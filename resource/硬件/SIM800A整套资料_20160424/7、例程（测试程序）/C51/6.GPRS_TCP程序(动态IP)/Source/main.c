/**********************************************************************************
 * 工程名  :GPRS
 * 描述    :测试GPRS连接，并且服务器可以通过GPRS控制LED
 * 实验平台:C51
 * 库版本  :
 * 作者    :泥人通信模块开发平台团队 
 * 博客    :http://nirenelec.blog.163.com
 * 淘宝    :http://shop105683814.taobao.com

 * 硬件连接说明
	 使用单片串口与GPRS模块通信 
	 C51        GPRS模块
	 P30 (RXD)->RXD
	 P31 (TXD)->TXD
	 GND	    ->GND


 * 软件功能说明
   板子上电后运行指示灯RUNING_LED会以一秒的频率闪烁
	 服务器发送“onled”点亮LED；
   服务器发送“offled”熄灭LED；
	 单片机每隔10秒会向服务器发送心跳帧“OK”
**********************************************************************************/
#include "config.h"
#include "string.h"
#include "delay.h"
#include "uart.h"

#define Automatic_Startup 1     //定义自启动 V1.2版本起有自启动功能

#define Buf1_Max 200 					  //串口2缓存长度
/*************	本地常量声明	**************/

const u8 *string = "AT+CIPSTART=\"TCP\",\"116.31.51.113\",9015";	//IP登录服务器
sbit RUNING_LED = P1^1;					//运行指示灯
sbit LED  			= P1^2;					//控制指示灯


/*************  本地变量声明	**************/
xdata u8 Uart1_Buf[Buf1_Max];

u8 Times=0,First_Int = 0,shijian=0;
u16 Heartbeat=0;
//u8 Time_count=0;
bdata u8 Flag;//定时器标志位
sbit Timer0_start =Flag^0;	//定时器0延时启动计数器
sbit Heart_beat		=Flag^1;	//发送心跳帧标志位

/*************	本地函数声明	**************/
void GPIO_config(void);
void Timer0Init(void);
void CLR_Buf2(void);
u8 Find(u8 *a);
void Second_AT_Command(u8 *b,u8 *a,u8 wait_time);
void Set_ATE0(void);
void Connect_Server(void);
void Rec_Server_Data(void);
void Wait_CREG(void);
void Send_OK(void);

/*************  外部函数和变量声明*****************/




/*******************************************************************************
* 函数名 : main 
* 描述   : 主函数
* 输入   : 
* 输出   : 
* 返回   : 
* 注意   : 串口波特率是9600，GPRS模块默认波特率是115200，需要自己通过串口助手修改
				   为9600方可使用。
*******************************************************************************/
void main(void)
{
	Timer0Init();  //初始化定时器0
	GPIO_config();
	EA=1;	//开总中断
	Uart1Init();    //初始化串口9600
	Wait_CREG();    //查询等待模块注册成功
	Set_ATE0();     //取消回显
	Connect_Server();//配置GPRS服务
	while(1)
	{
		Rec_Server_Data(); //接收服务器下发的数据，并处理
		
		if(Heart_beat)     //发送心跳帧
		{
			Send_OK();
			Heart_beat=0;
		}
	}
}

/*******************************************************************************
* 函数名 : Uart1 
* 描述   : 串口1中断服务入口函数
* 输入   : 
* 输出   : 
* 返回   : 
* 注意   : 
*******************************************************************************/
void Uart1() interrupt 4
{
    if (RI)
    {
      RI = 0;                           //清除RI位
			Uart1_Buf[First_Int] = SBUF;  	  //将接收到的字符串存到缓存中
			First_Int++;                			//缓存指针向后移动
			if(First_Int > Buf1_Max)       		//如果缓存满,将缓存指针指向缓存的首地址
			{
				First_Int = 0;
			}
    }
    if (TI)
    {
        TI = 0;                          //清除TI位
    }
}

/*******************************************************************************
* 函数名 : Timer0_ISR
* 描述   : 定时器0中断服务入口函数,20ms中断一次
* 输入   : 
* 输出   : 
* 返回   : 
* 注意   : 
*******************************************************************************/
void Timer0_ISR() interrupt 1
{
	static u8 Time_count=0; 
	TR0=0;//关定时器
	TL0 = 0x00;		//重设定时器初值
	TH0 = 0xB8;		//重设定时器初值
	Time_count++;
	if(Time_count>=50)//1秒运行指示灯闪烁
	{
		Time_count = 0;
		RUNING_LED =~RUNING_LED;
	}
	if(count_20ms) //20ms延时计数器
		count_20ms--;
	Heartbeat++;
	if(Heartbeat>500)//每10秒发送心跳帧
	{
		Heartbeat=0;
		Heart_beat=1;
	}
	if(Timer0_start)
	Times++;
	if(Times > (50*shijian))
	{
		Timer0_start = 0;
		Times = 0;
	}
	TR0=1;//开定时器
}
/*******************************************************************************
* 函数名 : GPIO_config
* 描述   : IO口配置函数
* 输入   : 
* 输出   : 
* 返回   : 
* 注意   : 
*******************************************************************************/
void	GPIO_config(void)
{
		LED=1;
		RUNING_LED=1;
}

/*******************************************************************************
* 函数名 : Timer0Init
* 描述   : 定时器0初始化，20ms定时
* 输入   : 
* 输出   : 
* 返回   : 
* 注意   : 
*******************************************************************************/
void Timer0Init(void)		//20毫秒@11.0592MHz
{
	AUXR &= 0x7F;		//定时器时钟12T模式
	TMOD &= 0xF0;		//
	TMOD |= 0x01;		//设置定时器模式，16位定时器
	TL0 = 0x00;		  //设置定时器初值
	TH0 = 0xB8;		  //设置定时器初值
	TF0 = 0;		    //清TF0标志
	TR0 = 1;		    //定时器0开始计时
	ET0 = 1;    	  //使能定时器0中断
}

/*******************************************************************************
* 函数名 : CLR_Buf1
* 描述   : 清除串口2缓存数据
* 输入   : 
* 输出   : 
* 返回   : 
* 注意   : 
*******************************************************************************/
void CLR_Buf1(void)
{
	u16 k;
	for(k=0;k<Buf1_Max;k++)      //将缓存内容清零
	{
		Uart1_Buf[k] = 0x00;
	}
    First_Int = 0;             //接收字符串的起始存储位置
}

/*******************************************************************************
* 函数名 : Wait_CREG
* 描述   : 等待模块注册成功
* 输入   : 
* 输出   : 
* 返回   : 
* 注意   : 
*******************************************************************************/
void Wait_CREG(void)
{
	u8 i;
	u8 k;
	i = 0;
	CLR_Buf1();
  while(i == 0)        			
	{
		CLR_Buf1();        
		UART1_SendString("AT+CREG?");
		UART1_SendLR();
		delay_ms(250);  						
	    for(k=0;k<Buf1_Max;k++)      			
    	{
			if(Uart1_Buf[k] == ':')
			{
				if((Uart1_Buf[k+4] == '1')||(Uart1_Buf[k+4] == '5'))
				{
					i = 1;
				  break;
				}
			}
		}
	}
}

/*******************************************************************************
* 函数名 : Find
* 描述   : 判断缓存中是否含有指定的字符串
* 输入   : 
* 输出   : 
* 返回   : unsigned char:1 找到指定字符，0 未找到指定字符 
* 注意   : 
*******************************************************************************/

u8 Find(u8 *a)
{ 
  if(strstr(Uart1_Buf,a)!=NULL)
	    return 1;
	else
			return 0;
}

/*******************************************************************************
* 函数名 : Second_AT_Command
* 描述   : 发送AT指令函数
* 输入   : 发送数据的指针、发送等待时间(单位：S)
* 输出   : 
* 返回   : 
* 注意   : 
*******************************************************************************/

void Second_AT_Command(u8 *b,u8 *a,u8 wait_time)         
{
	u8 i;
	u8 *c;
	c = b;										//保存字符串地址到c
	CLR_Buf1(); 
  i = 0;
	while(i == 0)                    
	{
		if(!Find(a)) 
		{
			if(Timer0_start == 0)
			{
				b = c;							//将字符串地址给b
				for (b; *b!='\0';b++)
				{
					UART1_SendData(*b);
				}
				UART1_SendLR();	
				Times = 0;
				shijian = wait_time;
				Timer0_start = 1;
		   }
    }
 	  else
		{
			i = 1;
			Timer0_start = 0;
		}
	}
	CLR_Buf1(); 
}

/*******************************************************************************
* 函数名 : Set_ATE0
* 描述   : 取消回显
* 输入   : 
* 输出   : 
* 返回   : 
* 注意   : 
*******************************************************************************/
void Set_ATE0(void)
{
	Second_AT_Command("ATE0","OK",3);								//取消回显		
}
/*******************************************************************************
* 函数名 : Connect_Server
* 描述   : GPRS连接服务器函数
* 输入   : 
* 输出   : 
* 返回   : 
* 注意   : 
*******************************************************************************/
void Connect_Server(void)
{
	UART1_SendString("AT+CIPCLOSE=1");	//关闭连接
	delay_ms(5);
	Second_AT_Command("AT+CIPSHUT","SHUT OK",2);		//关闭移动场景
	Second_AT_Command("AT+CGCLASS=\"B\"","OK",2);//设置GPRS移动台类别为B,支持包交换和数据交换 
	Second_AT_Command("AT+CGDCONT=1,\"IP\",\"CMNET\"","OK",2);//设置PDP上下文,互联网接协议,接入点等信息
	Second_AT_Command("AT+CGATT=1","OK",2);//附着GPRS业务
	Second_AT_Command("AT+CIPCSGP=1,\"CMNET\"","OK",2);//设置为GPRS连接模式
	Second_AT_Command("AT+CIPHEAD=1","OK",2);//设置接收数据显示IP头(方便判断数据来源,仅在单路连接有效)
	Second_AT_Command(string,"OK",5);	
	delay_ms(5);
	CLR_Buf1();
}
/*******************************************************************************
* 函数名 : Rec_Server_Data
* 描述   : 接收服务器数据函数
* 输入   : 
* 输出   : 
* 返回   : 
* 注意   : 
*******************************************************************************/
void Rec_Server_Data(void)
{
	if(strstr(Uart1_Buf,"+IPD")!=NULL)   		//若缓存字符串中含有^SISR
	{	
		Heartbeat=0;	//清除心跳帧计数器
		Heart_beat=0;
		delay_ms(10);
		if(strstr(Uart1_Buf,"onled")!=NULL)
		{
			LED = 0;
		}
		else if(strstr(Uart1_Buf,"offled")!=NULL)
		{
			LED = 1;
		}
		CLR_Buf1();
		Heart_beat=1;//发送应答数据，告诉服务器收到数据		
	}
}
/*******************************************************************************
* 函数名 : Send_OK
* 描述   : 发送数据应答服务器的指令，该函数在有两功能
					1：接收到服务器的数据后，应答服务器
					2：服务器无下发数据时，每隔10秒发送一帧心跳，保持与服务器连接
* 输入   : 
* 输出   : 
* 返回   : 
* 注意   : 
*******************************************************************************/
void Send_OK(void)
{
	Second_AT_Command("AT+CIPSEND",">",2);
	Second_AT_Command("OK\32\0","SEND OK",8);;			//回复OK
	delay_ms(10);
	CLR_Buf1(); 
}

