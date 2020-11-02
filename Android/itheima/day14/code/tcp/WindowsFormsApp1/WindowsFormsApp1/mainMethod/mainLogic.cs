using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Net;
using System.Net.Sockets;
using WindowsFormsApp1.util;
using System.Data;
using WindowsFormsApp1.data;
using System.Diagnostics;
using System.Threading;
using System.IO;
using System.Drawing.Imaging;
using System.Drawing;

namespace WindowsFormsApp1.mainMethod
{
    public class mainClass
    {
        //uidelegate
        public String adbPath;
        public Form1 frm;
        public Form2 frm2;
        public Thread thrMain;
        public String pathHome;
        public List<phone> listPhones;
        public Thread thrScreen;
        public Form2 f2;
        public Dictionary<String, String[]> dicPhones;

        public void iiiiinit(){ }

        public mainClass(Form1 frm)
        {
            //frm = new Form1();
            this.frm = frm;
            adbPath = get_adb_path();
            pathHome = System.Environment.CurrentDirectory;
        }

        public mainClass(Form2 frm2)
        {
            //frm = new Form1();
            this.frm2 = frm2;
            adbPath = get_adb_path();
            pathHome = System.Environment.CurrentDirectory;
        }

        public void mmmmain() { }


        /// <summary>
        /// Initializes display location, table sort, display location sort.
        /// </summary>
        /// <param name="form2"></param>
        /// <returns></returns>
    

        public List<phone> show_phone(Form2 form2) {

            int ParentStx = 250, ParentSty = 170;
            int maxX = 250, maxY = 170;

            f2 = form2;
            phone p = new phone();
            int stx = 0, sty = 0, phoneWidth = p.Width
                ,phoneHeight =p.Height ;

            if (listPhones != null) {
                foreach (phone pp in listPhones)
                {
                   f2.Tab_main.TabPages["Tpg_main"].Invoke(
                   new Action(() =>
                   {
                           Debug.Print("remove");
                           f2.Tab_main.TabPages["Tpg_main"].Controls.Remove(pp);
                   }));
                }
            }

            listPhones = new List<phone>();
            List<table> tables = ut.get_check_dgv_all( form2.Dgv_main, new table() );
            tables.Sort();
            form2.Dgv_main.Invoke(new Action(() => {
                form2.Dgv_main.DataSource = tables;
            }));


            adbPath = get_adb_path();
            List<String> listDevice = new List<string>();
            PhonePort phonePort = new PhonePort(9000);

            foreach (table tb in tables)
            {

                Debug.Print("show..in");
                p = new phone();
                p.colDevice = tb.colDevice;
                listDevice.Add( tb.colDevice);
                p.lbText = tb.colNum;
                p.Port = phonePort.getPort();

                // ut.cmd("reverse tcp:8600 tcp:9000", adbPath, tb.colDevice);
                 p.Location = new System.Drawing.Point(stx, sty);
                stx = stx + phoneWidth + 5;
                if (stx > form2.Tab_main.Width)
                {
                    stx = 0;
                    sty += phoneHeight + 5;
                }
 

                f2.Tab_main.TabPages["Tpg_main"].Invoke(
            new Action(() =>
            {
                Debug.Print("phones add to location");
                f2.Tab_main.TabPages["Tpg_main"].Controls.Add(p);
                Debug.WriteLine("当前控件location"+p.Location.X+","+p.Location.Y);

                int w = p.Location.X + ParentStx + p.Width;
                if (w > maxX) 
                    maxX = w;
                int h = p.Location.Y + ParentSty + p.Height + 5;
                if (h > maxY)
                    maxY = h;

            }));
                listPhones.Add(p);
        }

             dicPhones = new Dictionary<string, string[]>();
            foreach (phone phone in listPhones) {
                dicPhones[phone.colDevice] =new String[]{phone.Port.ToString(),"9000"};
            }

            ut.adb_forward(adbPath, dicPhones);

            /*    foreach (phone phone in listPhones)
            {
               
            }*/

            if (maxY > 1000)
                maxY = 1000;
            f2.Width = maxX;
            f2.Height = maxY;
          //  ut.cmd("reverse tcp:8600 tcp:9003", adbPath,listDevice);
            return listPhones;

        }


        public void ui_invoke(object a) {
            Type t = a.GetType();
            
        }

        public void thrSt(Thread thr)
        {
            thr.IsBackground = true;
            thr.Start();
        }
        public void thrDown(Thread thr)
        {
            if (thr != null)
            {
                thr.Abort();
            }
        }
        public void thrDown()
        {
            if (thrMain != null)
            {
                thrMain.Abort();
            }
        }

        public void socketSend(String str) {
            socketSend(str, 8000);
        }

        public void socketSend(String str, int port)
        {

            string hostName = Dns.GetHostName();   //获取本机名
            IPHostEntry localhost = Dns.GetHostByName(hostName);
            IPAddress localaddr = localhost.AddressList[0];
            Console.WriteLine(localaddr.ToString());
            IPEndPoint EPhost = new IPEndPoint(localaddr, port);
            byte[] buffer = new byte[2048];
            buffer = Encoding.Default.GetBytes(str);

            Socket socket = new Socket(AddressFamily.InterNetwork,
            SocketType.Stream, ProtocolType.Tcp);
            socket.Connect("127.0.0.1", port);
            socket.Send(buffer);
            socket.Close();
            Debug.WriteLine( "发送完毕" );
        }

        public List<table> fresh_adb_run()
        {

          frm.set_log("fresh_adb_run");
            String path = adbPath;
            Debug.WriteLine("adbPath=" + adbPath);
            List<String> retlist;
            List<table> listTable = new List<table>();
            String s = ut.adb_devices(path, out retlist);
            String colNum="";
            String pathSet = System.Environment.CurrentDirectory + "\\numSetting";
            String tp = pathSet;

            if (s == "ok")
            {
                foreach (var i in retlist)
                {
                    pathSet = tp +"\\"+i + ".txt";
                    colNum = ut.file_read(pathSet);
                    listTable.Add(new table(false, i,colNum));
                }
            }
            else
            {
                listTable = null;
            }

            frm.set_log("fresh_adb_run-finish");
            return listTable;

        }

        public void demo()
        {
            String path2 = System.Environment.CurrentDirectory;
            MessageBox.Show(path2);
            DataTable tables = new DataTable();
            List<table> list = new List<table>();

            //tables.Columns.Add("col_check", typeof(bool));
            //tables.Columns.Add("col_device", typeof(String));
            //table tb = new table();
            //tb.col_check = true;
            //tb.Col_device = "231";
            //list.Add(tb);
            //tb = new table();
            //tb.Col_device = "231";
            //tb.col_check = false;
            //list.Add(tb);

        }

        public String get_adb_path()
        {
            String path = ut.getPathByProcessName("adb");
            if (path != null)
            {
                path = path.Replace("\\adb.exe", "");
                adbPath = path;
               // frm2.set_log("get_adb_path=" + path);
                return path;
            }
            else
            {
                path = System.Environment.CurrentDirectory + "\\adb";
                adbPath = path;
               // frm2.set_log("get_adb_path=" + path);
                return path;
            }
        }
    }


}
