using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApp1.util
{
    /// <summary>
    /// 建立TCP通信监听服务
    /// </summary>
    internal class TCPListener
    {
        private static Socket serviceSocketListener;

        public Form2 form2;
        public phone ph;
        public List<phone> listPhones;
        public Dictionary<String, phone> dicPhones;
        public Socket socketC;

        public void iiiiii() { }
        public TCPListener(Form2 form2) {
            this.form2 = form2;
        }

        public TCPListener(phone ph)
        {
            // this.form2 = form2;
            this.ph = ph;
        }

        public void socketClient()
        {

            string hostName = Dns.GetHostName();   //获取本机名
            IPHostEntry localhost = Dns.GetHostByName(hostName);
            IPAddress localaddr = localhost.AddressList[0];
            Console.WriteLine(localaddr.ToString());
            IPEndPoint EPhost = new IPEndPoint(IPAddress.Any, ph.Port);
            socketC = new Socket(AddressFamily.InterNetwork,
            SocketType.Stream, ProtocolType.Tcp);
            socketC.Connect("127.0.0.1", ph.Port);
            Thread tp = new Thread( AcceptMgs );
            tp.IsBackground = true;
            tp.Start();

        }

        public void socketClient(int port)
        {

            string hostName = Dns.GetHostName();   //获取本机名
            IPHostEntry localhost = Dns.GetHostByName(hostName);
            IPAddress localaddr = localhost.AddressList[0];
            Console.WriteLine(localaddr.ToString());
            IPEndPoint EPhost = new IPEndPoint(IPAddress.Any, port);
            socketC = new Socket(AddressFamily.InterNetwork,
            SocketType.Stream, ProtocolType.Tcp);
            socketC.Connect("127.0.0.1", port);
            Thread tp = new Thread(AcceptMgs);
            tp.IsBackground = true;
            tp.Start();

        }

        /* private void AcceptMgs()
         {
             try
             {
                 /// <summary>
                 /// 存储大文件的大小
                 /// </summary>
                 long length = 0;
                 long recive = 0; //接收的大文件总的字节数
                 String filePath="";

                 FileStream fsWrite;
                 while (true)
                 {
                     Debug.WriteLine("receive");
                     byte[] buffer = new byte[1024 ];
                     int r = socketC.Receive(buffer);
                     //Debug.WriteLine("Recerive-----success");
                     //if (r == 0)
                     //{
                     //    break;
                     //}
                     //length = int.Parse(Encoding.UTF8.GetString(buffer, 0, r));
                     //recive = length;
                     filePath = "C:\\1.jpg";
                     if (r > 0)  //判断大文件是否已经保存完
                     {
                         fsWrite = new FileStream(filePath, FileMode.Create, FileAccess.Write);
                         //保存接收的文件
                          while (true) { 
                             fsWrite.Write(buffer, 0, r);
                             //Debug.WriteLine("保存文件" + r);
                             r = socketC.Receive(buffer);
                             if (r<= 0)
                             {
                                 Debug.WriteLine(socketC.RemoteEndPoint + ": 接收文件成功");
                                 fsWrite.Close();
                                 // socketC.Send(System.Text.Encoding.Default.GetBytes("ok"));
                                 socketC.Close();
                                 socketC = new Socket(AddressFamily.InterNetwork,
                                 SocketType.Stream, ProtocolType.Tcp);
                                 socketC.Connect("127.0.0.1", 8001);
                                 break;
                             }
                         }
                         //length -= r; //减去每次保存的字节数
                         // Debug.WriteLine(string.Format("{0}: 已接收：{1}/{2}", socketSend.RemoteEndPoint, recive - length, recive));
                     }

                     //if (buffer[0] == 1) //如果接收的字节数组的第一个字节是1，说明接收的是文件
                     //{

                     //}
                 }
             }
             catch { }
         }*/

        private void AcceptMgs()
        {
            try
            {
                /// <summary>
                /// 存储大文件的大小
                /// </summary>
                long length = 0;
                long recive = 0; //接收的大文件总的字节数
                String filePath = "c:\\1.jpg";
                /* if (File.Exists(filePath)) {
                    File.Delete(filePath);
                }*/

                //FileStream fsWrite;
                //fsWrite = new FileStream(filePath, FileMode.Create, FileAccess.Write);
                FileStream fsWrite = null;
                while (true)
                {
                    Debug.WriteLine("loop");
                  
                    byte[] data = ReceiveVarData(socketC);
                    if (data.Length != 0)
                    {
                        ph.Invoke((EventHandler)delegate
                        {
                            ph.Pbx_phone.Image = Image.FromStream( new MemoryStream(data));
                        });
                        //socketC.Send(System.Text.Encoding.Default.GetBytes("ok\r\n"));
                        socketC.Close();
                        socketC = new Socket(AddressFamily.InterNetwork,
                        SocketType.Stream, ProtocolType.Tcp);
                        socketC.Connect("127.0.0.1", ph.Port);
                        //socketC.Send();
                    }
                    else
                    {
                        Debug.WriteLine("没有数据");
                    }
                }
            }
            catch (Exception e)
            {
                Debug.WriteLine(e.Message);
            }
        }

        private static byte[] ReceiveVarData(Socket s)
        {
            int total = 0;
            int recv;
            byte[] datasize = new byte[4];
            recv = s.Receive(datasize, 0, 4, SocketFlags.None);

            int size = BitConverter.ToInt32(datasize, 0);
            int dataleft = size;
            byte[] data = new byte[size];

            while (true)
            {
                //Debug.WriteLine("开始接受.." + total + "/" + dataleft);
                recv = s.Receive(data, total, dataleft, SocketFlags.None);
               // recv = s.Receive(data);
                Debug.WriteLine("开始接受.." + total + "/"+size);

                if (recv == 0)
                {
                    Debug.WriteLine("接受完成");
                    break;
                }

                total += recv;
                dataleft -= recv;
                /* if (total > size) {
                     break;
                 }*/
                if (dataleft <1) {
                    break;
                   
                }    
            }
            return data;

        }

        public TCPListener(Form2 form2,List<phone> listPhones)
        {
            this.form2 = form2;
            this.listPhones = listPhones;
        }

       
        public void flush_dicPhones( List<phone> listPhones ) {
            dicPhones.Clear();
            foreach (phone ph in listPhones)
            {
                dicPhones.Add(ph.colDevice, ph);
            }
        }

        public void Init()
        {

            Debug.WriteLine("Init--dic--phones");
            dicPhones = new Dictionary<string,phone>();

            foreach (phone ph in listPhones ) 
            {
                dicPhones.Add(ph.colDevice, ph);
            }

            Socket socketWatch = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            IPAddress ip = IPAddress.Any;//IPAddress.Parse(txtServer.Text);
            //创建端口号对象
            IPEndPoint point = new IPEndPoint(ip, Convert.ToInt32(9003));

            //监听
            socketWatch.Bind(point);
            showMsg("监听成功");
            socketWatch.Listen(2000);
            Thread th = new Thread( Listen );
            th.IsBackground = true;
            th.Start( socketWatch );

            //Thread tt = new Thread(Listen);
            //tt.IsBackground = true;
            //tt.Start(socketWatch);
            //th.Start(socketWatch);


        }

        Socket socketSend;
        Dictionary<string, Socket> dicSocket = new Dictionary<string, Socket>();
        void Listen(object o)
        {
            Socket socket = o as Socket;
            //等待客户端的连接 并且创建一个负责通信的Socket
            while (true)
            {
                try
                {
                    Debug.WriteLine("开始监听");
                    Socket clientSocket = socket.Accept();
                    Thread th = new Thread( Recive );
                    th.IsBackground = true;
                    th.Start( clientSocket );
                }
                catch
                { }

            }
        }

        void Recive(object o)
        {
                Socket clientSocket = o as Socket;
                 String strDevice;    

                try
                {
                Debug.WriteLine("接受成功");
                MemoryStream ms = new MemoryStream();
               
                    Debug.WriteLine("客户端已连接");
                    int size = 0;
                    ms = new MemoryStream();
                    byte[] c;
                    while (true)
                    {
                        byte[] bits = new byte[500];
                        //Debug.Print("aaa");
                        int r = clientSocket.Receive(bits, bits.Length, SocketFlags.None);
                        if (r <= 0)
                            break;
                        ms.Write(bits, 0, r);
                    }
                    byte[] bt = ms.ToArray();

                    //112,105,99 分隔获取图片和硬件码
                    byte[] str = null;
                    byte[] pic = null;

                    for (int i = 0; i < bt.Length; i++)
                    {
                        if (bt[i] == 112)
                        {
                            if (bt[i + 1] == 105 && bt[i + 2] == 99)
                            {
                                str = bt.Take(i).ToArray();
                                pic = bt.Skip(i + 3).ToArray();
                                break;
                            }
                        }
                    }

                if (str == null) {
                    return;
                }

                    strDevice = Encoding.Default.GetString(str);
                    Debug.WriteLine("Device="+strDevice);
                    ms.Close();
                    ms = new MemoryStream(pic);

                    //Image image = Bitmap.FromStream(ms, true);
                    Image image = Image.FromStream(ms);
                    ph = dicPhones[strDevice];

                    ph.Pbx_phone.Invoke((EventHandler)delegate{
                        ph.Pbx_phone.Image = image;
                    });

                    //form2.Pbx_test.Invoke((EventHandler)delegate
                    //{
                    //    form2.Pbx_test.Image = image; //更新在窗体控件上
                    //});

                    ms.Dispose();
                    Debug.WriteLine("接受完成");

                


            }
                catch
                { }
           
        }

        public void showMsg(String str)
        {
            Debug.WriteLine(str);
        }
    }
}
