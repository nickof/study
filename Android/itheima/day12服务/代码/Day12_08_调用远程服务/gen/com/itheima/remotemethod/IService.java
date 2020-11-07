/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\itheima\\基础_黑马95\\day12\\代码\\Day12_08_调用远程服务\\src\\com\\itheima\\remotemethod\\IService.aidl
 */
package com.itheima.remotemethod;
public interface IService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.itheima.remotemethod.IService
{
private static final java.lang.String DESCRIPTOR = "com.itheima.remotemethod.IService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.itheima.remotemethod.IService interface,
 * generating a proxy if needed.
 */
public static com.itheima.remotemethod.IService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.itheima.remotemethod.IService))) {
return ((com.itheima.remotemethod.IService)iin);
}
return new com.itheima.remotemethod.IService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_callRemoteMethod:
{
data.enforceInterface(DESCRIPTOR);
this.callRemoteMethod();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.itheima.remotemethod.IService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void callRemoteMethod() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_callRemoteMethod, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_callRemoteMethod = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void callRemoteMethod() throws android.os.RemoteException;
}
