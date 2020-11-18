from urllib import request
# 刚才复制的url放在这里
api="http://t33.tingchina.com/yousheng/%E7%8E%84%E5%B9%BB%E5%A5%87%E5%B9%BB/%E5%A4%AA%E5%8F%A4%E7%A5%9E%E7%8E%8B/0686.mp3?key=539d1a4409180bafbb00483d600b53b5_628199010"
path = '/Users/liko/study/python/mp3download/mp3/0686.mp3'
request.urlretrieve(api, path)
print('下载完成')

