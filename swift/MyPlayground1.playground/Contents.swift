//: A UIKit based Playground for presenting user interface
  

CGEventRef myCallBack(CGEventTapProxy proxy, CGEventType type, CGEventRef event, void *userInfo)
{
    
    UniCharCount actualStringLength = 0;
    UniChar inputString[128];
    CGEventKeyboardGetUnicodeString(event, 128, &actualStringLength, inputString);
    NSString* inputedString = [[NSString alloc] initWithBytes:(const void*)inputString length:actualStringLength encoding:NSUTF8StringEncoding];
    
    CGEventFlags flag = CGEventGetFlags(event);
    NSLog(@"inputed string:%@, flags:%lld", inputedString, flag);
    return event;
}

CFRunLoopRef theRL = CFRunLoopGetCurrent();
CFMachPortRef keyUpEventTap = CGEventTapCreate(kCGSessionEventTap, kCGHeadInsertEventTap ,kCGEventTapOptionListenOnly,CGEventMaskBit(kCGEventKeyUp) | CGEventMaskBit(kCGEventFlagsChanged),&myCallBack,NULL);
CFRunLoopSourceRef keyUpRunLoopSourceRef = CFMachPortCreateRunLoopSource(NULL, keyUpEventTap, 0);
CFRelease(keyUpEventTap);
CFRunLoopAddSource(theRL, keyUpRunLoopSourceRef, kCFRunLoopDefaultMode);
CFRelease(keyUpRunLoopSourceRef);

