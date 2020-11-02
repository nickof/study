using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

 namespace PreInstaller.IO
{

    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Text;
    using System.Net;
    using System.Net.Sockets;

    class SocketServe
    {
        public string listen()
        {
            
            Socket client = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            IPAddress myIP = IPAddress.Parse("127.0.0.1");
            IPEndPoint EPhost = new IPEndPoint(myIP, int.Parse("8000"));
            client.Connect(EPhost);
            byte[] t_data = new byte[1024];
            string data = null;
            int i = 0;

            String string1 = "测试数据123测试数据ABC";
            byte[] buffer = new byte[2048];
            buffer = Encoding.Default.GetBytes(string1);

            client.Send(buffer);
            //while ((i = client.Receive(t_data)) != 0)
            //{
            //    data = Encoding.UTF8.GetString(t_data, 0, i);
            //}
            client.Close();
            return data;

        }


    }
}
