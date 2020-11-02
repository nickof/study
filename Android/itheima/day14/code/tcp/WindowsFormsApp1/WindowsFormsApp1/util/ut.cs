using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Management;
using System.Diagnostics;
using System.IO;
using System.Windows.Forms;
using System.Text.RegularExpressions;
using System.Threading;
using System.Xml;
using System.Net.NetworkInformation;
using System.Net;
using WindowsFormsApp1.data;
using System.Reflection;

namespace WindowsFormsApp1.util
{
    class ut
    {

        //public String receive;
        public static Process p = null;
        public static Thread thrLog;
        //public static Label log = Form1.uilog;
        public static XmlDocument xmlDoc = null;
        public static Label log = Form2.uilog;
        //get_xml_value
        //set_xml_value
        //file_read
        //file_write
        public void iiiiiinit() { }

        #region 端口枚举类型
        /// <summary>
        /// 端口类型
        /// </summary>
        public enum  EportType
        {
            /// <summary>
            /// TCP类型
            /// </summary>
            TCP,
            /// <summary>
            /// UDP类型
            /// </summary>
            UDP
        }
        #endregion

        #region 指定类型的端口是否已经被使用了
        /// <summary>
        /// 指定类型的端口是否已经被使用了
        /// </summary>
        /// <param name="port">端口号</param>
        /// <param name="type">端口类型</param>
        /// <returns></returns>
        public static bool port_in_use(int port, EportType type)
        {
            bool flag = false;
            IPGlobalProperties properties = IPGlobalProperties.GetIPGlobalProperties();
            IPEndPoint[] ipendpoints = null;
            if (type == EportType.TCP)
            {
                ipendpoints = properties.GetActiveTcpListeners();
            }
            else
            {
                ipendpoints = properties.GetActiveUdpListeners();
            }
            foreach (IPEndPoint ipendpoint in ipendpoints)
            {
                if (ipendpoint.Port == port)
                {
                    flag = true;
                    break;
                }
            }
            ipendpoints = null;
            properties = null;
            return flag;
        }
        #endregion


 

    public static void crForder( String path) {
            if (Directory.Exists(path) == false)//如果不存
                Directory.CreateDirectory(path);
        }

        public static void adb_pull(String cmdPath,String device,String pathPhone,String pathPc) {
            String cmdStr = "pull " + pathPhone + " " + pathPc;
            
            cmd(cmdStr, cmdPath, device);
        }

        public static  void thr_down(Thread thr)
        {
            if (thr != null)
            {
                thr.Abort();
            }
        }
        public static void thr_st(Thread thr)
        {
            thr.IsBackground = true;
            thr.Start();
        }
        
        public static List<T> get_check_dgv_all<T>(DataGridView dgv,T t )
        {
            List<T> list = new List<T>();
            Type type = typeof(T);
            List<String> colName=new List<string>();

            for (int i = 0; i < dgv.Columns.Count; i++) {
                Debug.Print(dgv.Columns[i].Name);
                colName.Add (dgv.Columns[i].Name);
            }
            PropertyInfo[] propertys=type.GetProperties();

            PropertyInfo tp = null; 
            for (int i = 0; i < dgv.Rows.Count; i++) {
                Object obj = Activator.CreateInstance(type);
                for (int j = 0; j < colName.Count; j++){
                    tp = type.GetProperty(colName[j] );
                    if (tp != null) {
                        Debug.Print("列名=.." + colName[j]);
                        tp.SetValue(obj, dgv.Rows[i].Cells[colName[j]].Value, null);
                    }
                }
                list.Add((T)obj);
            }
            return list;
        }

        

        public static bool PortInUse(int port)
        {
            bool inUse = false;
            IPGlobalProperties ipProperties = IPGlobalProperties.GetIPGlobalProperties();
            IPEndPoint[] ipEndPoints = ipProperties.GetActiveTcpListeners();

            foreach (IPEndPoint endPoint in ipEndPoints)
            {
                if (endPoint.Port == port)
                {
                    inUse = true;
                    break;
                }
            }
            return inUse;
        }

        public static void file_write(String path, String text) {

            if (File.Exists(path))
            {
                FileStream stream2 = File.Open(path, FileMode.OpenOrCreate, FileAccess.Write);
                stream2.Seek(0, SeekOrigin.Begin);
                stream2.SetLength(0); //清空txt文件
                StreamWriter sw = new StreamWriter(stream2);
                sw.Write(text);
                sw.Flush();
                sw.Close();
                stream2.Close();
            }
            else
            {
                FileStream fs = new FileStream(path, FileMode.CreateNew);
                StreamWriter sw = new StreamWriter(fs);
                sw.Write(text);
                sw.Flush();
                sw.Close();
                fs.Close();
            }

        }

        public static String file_read(String path)
        {
            String str = "";
            if (File.Exists(path))
            {
                StreamReader sr = new StreamReader(path, Encoding.UTF8);
                String line;
                while ((line = sr.ReadLine()) != null)
                {
                    str += line;
                }
                sr.Close();
            }
            return str;
        }

        public static string cmdWait(string Command, String path, List<String> devicelist)
        {
            // Command = Command.Trim().TrimEnd('&') + "&exit";
            ui_setlog("cmd-run-Command=" + Command + ",path=" + path + "");
            Debug.Print(path);

            if (p == null) p = new Process();
            {

                Command = Command.Trim();
                String cmdEnterPath = "cd /d " + path + " & ";

                p.StartInfo.FileName = "cmd.exe";
                p.StartInfo.UseShellExecute = false;        //是否使用操作系统shell启动
                p.StartInfo.RedirectStandardInput = true;   //接受来自调用程序的输入信息
                p.StartInfo.RedirectStandardOutput = true;  //由调用程序获取输出信息
                p.StartInfo.RedirectStandardError = true;   //重定向标准错误输出
                p.StartInfo.CreateNoWindow = true;          //不显示程序窗口
                p.Start();//启动程序
                          //向cmd窗口写入命令
                          //String cmdStr = "shell am start - n " + value;
                          //ut.cmd(cmdStr, adbPath, device);
                String tpCmd;
                StreamReader reader;
                StreamReader error;
                string str = "";

                foreach (var device in devicelist)
                {
                    tpCmd = cmdEnterPath + "adb -s " + device + " " + Command;
                    Debug.Print("集合运行cmd=" + tpCmd);
                    //.TrimEnd('&') + "&exit"
                    p.StandardInput.WriteLine(tpCmd);
                    p.StandardInput.AutoFlush = true;
                    ui_setlog("device=" + device + ":Command=" + tpCmd);
                    //获取cmd窗口的输出信息
                    //reader = p.StandardOutput;//截取输出流
                    //error = p.StandardError;//截取错误信息
                    //str = reader.ReadToEnd() + error.ReadToEnd();
                    //p.StandardInput.WriteLine("exit");
                    //线程起点
                    //p.WaitForExit();//等待程序执行完退出进程
                }
                //reader = p.StandardOutput;//截取输出流
                //error = p.StandardError;//截取错误信息 
                p.StandardInput.WriteLine("exit");
                ui_setlog("cmd_list_out");
                p.Close();
                return str;
            }
        }

        public static String getPathByProcessName(String name)
        {

            Process[] localByName = Process.GetProcessesByName(name);
            if (localByName.Length < 1)
            {
                return null;
            }
            return localByName[0].MainModule.FileName;
        }

        public static void ui_setlog(String str)
        {

            if (thrLog != null)
            {
                thrLog.Abort();
            }
            thrLog = new Thread(() =>
            {
                Action<String> action = (data) =>
                {
                    log.Text = data.ToString() + ":" + DateTime.Now.ToLongTimeString().ToString();
                };
                log.Invoke(action, str);
            });
            thrLog.Start();
        }

        public static String[] split(String str, String split_)
        {
            String[] strArr = Regex.Split(str, split_, RegexOptions.IgnoreCase);
            return strArr;
        }

        public static String get_xml_value(String path, String rootname) {

            if (xmlDoc == null)
                xmlDoc = new XmlDocument();
            XmlReaderSettings settings = new XmlReaderSettings();
            settings.IgnoreComments = true;       //忽略文档里面的注释

            XmlReader reader = XmlReader.Create(path, settings);
            xmlDoc.Load(reader);
            reader.Close();
            return xmlDoc.GetElementsByTagName(rootname)[0].Attributes["id"].Value;
            //打印节点属性值
            //Debug.Print(x.GetElementsByTagName("Tb_app_activity")[0].Name);//打印节点名称
            //Debug.Print(x.GetElementsByTagName("library")[0].Attributes["id"].Value);//打印节点属性值

        }

        public static void xml_set_value(String path, String rootname, String value)
        {

            if (xmlDoc == null)
                xmlDoc = new XmlDocument();
            XmlReaderSettings settings = new XmlReaderSettings();
            settings.IgnoreComments = true;       //忽略文档里面的注释
            XmlReader reader = XmlReader.Create(path, settings);
            xmlDoc.Load(reader);
            xmlDoc.GetElementsByTagName(rootname)[0].Attributes["id"].Value = value;
            xmlDoc.Save(path);

            //打印节点属性值
            //Debug.Print(x.GetElementsByTagName("Tb_app_activity")[0].Name);//打印节点名称
            //Debug.Print(x.GetElementsByTagName("library")[0].Attributes["id"].Value);//打印节点属性值

        }

        public static void set_xml_value(String path, Dictionary<String, String> dic)
        {
            if (xmlDoc == null)
                xmlDoc = new XmlDocument();
            XmlReaderSettings settings = new XmlReaderSettings();
            settings.IgnoreComments = true;       //忽略文档里面的注释
            XmlReader reader = XmlReader.Create(path, settings);
            xmlDoc.Load(reader);
            foreach (KeyValuePair<String, String> item in dic) {
                xmlDoc.GetElementsByTagName(item.Key)[0].Attributes["id"].Value = item.Value;
            }
            reader.Close();
            xmlDoc.Save(path);
        }

        
        /// <summary>
        /// adb devices
        /// </summary>
        /// <param name="path">adb路径</param>
        /// <param name="retlist">返回包含device的list</param>
        /// <returns>empty无设备,err adb错误,ok正常返回</returns>
        public static String adb_devices(String path, out List<String> retlist) {

            string str = cmd("devices", path);
            str = str.Trim();
            Debug.Print("adb-device返回--" + str);
            String[] strArr = ut.split(str, "List of devices attached\r\n");
            if (strArr.Length < 2) {
                strArr= ut.split(str, "List of devices attached \r\n");
            }

            Debug.Print(strArr[0]);
            String ret = "ok";
            retlist = new List<string>();

            if (strArr.Length < 2)
            {
                MessageBox.Show(str);
                return "err";
            }
            else if (strArr[1] == "")
            {
                return "empty";
            }

            strArr = ut.split(strArr[1].Trim(), "\r" +"\n");
            String[] strArrSub;
            foreach (var s in strArr) {
                strArrSub = ut.split(s, "\t");
                Debug.Print(strArrSub[0]);
                retlist.Add(strArrSub[0]);
            }
            return "ok";

        }

        /// <summary>
        /// Returns adbpath based on the process name
        /// If no default exepath+adb path is generated 
        /// </summary>
        /// <returns>adb path</returns>
        public static String get_adb_path()
        {
            String path = ut.getPathByProcessName("adb");
            if (path != null)
            {
                path = path.Replace("\\adb.exe", "");
                Debug.WriteLine("get_adb_path=" + path);
                return path;
            }
            else
            {
                path = System.Environment.CurrentDirectory + "\\adb";
                Debug.WriteLine("get_adb_path=" + path);
                return path;
            }
        }

        public static List<table> adb_devices()
        {

            Debug.WriteLine("adb_devices");
            String path = get_adb_path();
            Debug.WriteLine("adbPath=" + path);
            List<String> retlist;
            List<table> listTable = new List<table>();

            String s = ut.adb_devices(path, out retlist);
            String colNum = "";
            String pathSet = System.Environment.CurrentDirectory + "\\numSetting";
            String tp = pathSet;


            if (s == "ok")
            {
                foreach (var i in retlist)
                {
                    pathSet = tp + "\\" + i + ".txt";
                    colNum = ut.file_read( pathSet );
                    listTable.Add( new table( false, i, colNum ) );
                }
            }
            else
            {
                listTable = null;
            }
            Debug.WriteLine("fresh_adb_run-finish");
            return listTable;

        }

        public static void adb_forward(String path,Dictionary<String,String[]> devices) {
            // adb -s deviece forward tcp:+port1 tcp:port2

            // Command = Command.Trim().TrimEnd('&') + "&exit";
            if (p == null) p = new Process();
            String cmdEnterPath = "cd /d " + path + " & ";
            p.StartInfo.FileName = "cmd.exe";
            p.StartInfo.UseShellExecute = false;        //是否使用操作系统shell启动
            p.StartInfo.RedirectStandardInput = true;   //接受来自调用程序的输入信息
            p.StartInfo.RedirectStandardOutput = true;  //由调用程序获取输出信息
            p.StartInfo.RedirectStandardError = true;   //重定向标准错误输出
            p.StartInfo.CreateNoWindow = true;          //不显示程序窗口
            p.Start();

            String finalCommand = cmdEnterPath;
            int i = 0;

            foreach (var device in devices) {
                if (i==0)
                {
                    finalCommand += "adb -s " + device.Key +
                        " forward tcp:" + device.Value[0] + " tcp:" + device.Value[1];
                }
                else
                {
                    finalCommand += "&adb -s " + device.Key +
                 " forward tcp:" + device.Value[0] + " tcp:" + device.Value[1];
                }
                i++;
            }
            finalCommand += "&exit";
            Debug.WriteLine("adb forward final command=" + finalCommand);
            p.StandardInput.AutoFlush = true;
            p.StandardInput.WriteLine(finalCommand);
            p.Close();
        
        }

        public static void cmd(string[] Command, String path, List<String> devicelist)
        {
            // Command = Command.Trim().TrimEnd('&') + "&exit";
            ut.process_kill_by_name("cmd");
            ui_setlog("cmd[][]-run-Command=" + Command + ",path=" + path + "");
            Debug.Print(path);

            if (p == null) p = new Process();
            {
                String cmdEnterPath = "cd /d " + path + " & ";
                p.StartInfo.FileName = "cmd.exe";
                p.StartInfo.UseShellExecute = false;        //是否使用操作系统shell启动
                p.StartInfo.RedirectStandardInput = true;   //接受来自调用程序的输入信息
                p.StartInfo.RedirectStandardOutput = true;  //由调用程序获取输出信息
                p.StartInfo.RedirectStandardError = true;   //重定向标准错误输出
                p.StartInfo.CreateNoWindow = true;          //不显示程序窗口
                p.Start();

                //启动程序
                //向cmd窗口写入命令
                //String cmdStr = "shell am start - n " + value;
                //ut.cmd(cmdStr, adbPath, device);
                String tpCmd;
                StreamReader reader;
                StreamReader error;
                string str = "";
                String finalCommand = "";
                for (int i = 0; i < Command.Length; i++) {
                    if (i == 0)
                        finalCommand += "adb -s {0} " + Command[i];
                    else
                        finalCommand += " & " + "adb -s {0} " + Command[i];
                }

                foreach (var device in devicelist)
                {
                    tpCmd = String.Format(cmdEnterPath + finalCommand, device);
                    tpCmd = tpCmd.Replace("\r\n", "");
                        Debug.Print("cmd[][]=" + tpCmd);
                        //.TrimEnd('&') + "&exit"
                        p.StandardInput.WriteLine(tpCmd);
                        p.StandardInput.AutoFlush = true;
                }
            }
            
            //reader = p.StandardOutput;//截取输出流
            //error = p.StandardError;//截取错误信息 
            //p.StandardInput.WriteLine("exit");
            ui_setlog("cmd_list_command[]_out");
            p.Close();
            //return "";
        }

        public static string cmd(string Command, String path, List<String> devicelist)
        {
            // Command = Command.Trim().TrimEnd('&') + "&exit";
            //ut.process_kill_by_name("cmd");

            //ui_setlog("cmd-run-Command=" + Command + ",path=" + path + "");
       
            Debug.Print(path);

            if (p == null) p = new Process();
            {
                Command = Command.Trim();
                String cmdEnterPath = "cd /d " + path + " & ";
                p.StartInfo.FileName = "cmd.exe";
                p.StartInfo.UseShellExecute = false;        //是否使用操作系统shell启动
                p.StartInfo.RedirectStandardInput = true;   //接受来自调用程序的输入信息
                p.StartInfo.RedirectStandardOutput = true;  //由调用程序获取输出信息
                p.StartInfo.RedirectStandardError = true;   //重定向标准错误输出
                p.StartInfo.CreateNoWindow = true;          //不显示程序窗口
                p.Start();//启动程序
                          //向cmd窗口写入命令
                          //String cmdStr = "shell am start - n " + value;
                          //ut.cmd(cmdStr, adbPath, device);
                String tpCmd="";
                StreamReader reader;
                StreamReader error;
                string str = "";

                int first = 0;
                foreach (var device in devicelist)
                {
                    if (first == 0)
                    {
                        tpCmd = cmdEnterPath + "adb -s " + device + " " + Command;
                        ++first;
                    }
                    else
                    {
                        tpCmd += "&adb -s " + device + " " + Command;
                    }
                }
                Debug.Print("集合运行cmd=" + tpCmd);
                tpCmd += "&exit";
                //.TrimEnd('&') + "&exit"
                p.StandardInput.WriteLine(tpCmd);
                p.StandardInput.AutoFlush = true;
                //ui_setlog("Command=" + tpCmd);
                Debug.WriteLine("final command=" + tpCmd);

                p.StandardInput.WriteLine("exit");
                p.Close();
                return str;

            }
        }

        public static String cmd(string Command, String path) {
       return  cmd(Command, path, "");
    }
        public static string cmd(string Command, String path, String device)
        {
           // Command = Command.Trim().TrimEnd('&') + "&exit";
            Debug.Print(path);
            ut.process_kill_by_name("cmd");


            if (p == null) p = new Process();
            {
                
                Command = Command.Trim();
                if (device != "") Command = "adb -s " + device + " " + Command;
                else Command = "adb " + Command;
                
                if (path!="")
                {
                    Command = "cd /d " + path + " & " + Command;
                }
                Command = Command.Trim().TrimEnd('&') + "&exit";
                Debug.Print("Command=" + Command);
     
                p.StartInfo.FileName = "cmd.exe";
                p.StartInfo.UseShellExecute = false;        //是否使用操作系统shell启动
                p.StartInfo.RedirectStandardInput = true;   //接受来自调用程序的输入信息
                p.StartInfo.RedirectStandardOutput = true;  //由调用程序获取输出信息
                p.StartInfo.RedirectStandardError = true;   //重定向标准错误输出
                p.StartInfo.CreateNoWindow = true;          //不显示程序窗口
                p.Start();//启动程序
                          //向cmd窗口写入命令
                p.StandardInput.WriteLine(Command);
                p.StandardInput.AutoFlush = true;
                //获取cmd窗口的输出信息
                StreamReader reader = p.StandardOutput;//截取输出流
                StreamReader error = p.StandardError;//截取错误信息
                //线程起点
                string str = reader.ReadToEnd() + error.ReadToEnd();
                p.WaitForExit();//等待程序执行完退出进程
                p.Close();
                return str;
            }
        }

        public static void cmd_no_return(string Command, String path, String device)
        {
            // Command = Command.Trim().TrimEnd('&') + "&exit";
            Debug.Print(path);

            if (p == null) p = new Process();
            {

                Command = Command.Trim();
                if (device != "") Command = "adb -s " + device + " " + Command;
                else Command = "adb " + Command;

                if (path != "")
                {
                    Command = "cd /d " + path + " & " + Command;
                }
                Command = Command.Trim().TrimEnd('&') + "&exit";
                Debug.Print("Command=" + Command);

                p.StartInfo.FileName = "cmd.exe";
                p.StartInfo.UseShellExecute = false;        //是否使用操作系统shell启动
                p.StartInfo.RedirectStandardInput = true;   //接受来自调用程序的输入信息
                p.StartInfo.RedirectStandardOutput = true;  //由调用程序获取输出信息
                p.StartInfo.RedirectStandardError = true;   //重定向标准错误输出
                p.StartInfo.CreateNoWindow = true;          //不显示程序窗口
                p.Start();//启动程序
                          //向cmd窗口写入命令
                p.StandardInput.WriteLine(Command);
                p.StandardInput.AutoFlush = true;
                //获取cmd窗口的输出信息
                //StreamReader reader = p.StandardOutput;//截取输出流
                //StreamReader error = p.StandardError;//截取错误信息
                //线程起点
                //string str = reader.ReadToEnd() + error.ReadToEnd();
               //等待程序执行完退出进程
                p.Close();
            }
        }

        /*
        public static String cmd(String command,String path,String device) 
                {
            String cmd = path+"\\adb.exe";
      
            Process p = new Process();
            p.StartInfo.FileName = cmd;           //设定程序名  
            if (device == "")
                p.StartInfo.Arguments = command;    //设定程式执行參數  
            else
                p.StartInfo.Arguments =" -s "+device+" "+command;

            Debug.Print("cmd命令为:" + p.StartInfo.Arguments);
            p.StartInfo.UseShellExecute = false;        //关闭Shell的使用  
            p.StartInfo.RedirectStandardInput = true;   //重定向标准输入  
            p.StartInfo.RedirectStandardOutput = true;  //重定向标准输出  
            p.StartInfo.RedirectStandardError = true;   //重定向错误输出  
            p.StartInfo.CreateNoWindow = true;          //设置不显示窗口  
            p.Start();
            p.StandardInput.AutoFlush = true;
            StreamReader reader = p.StandardOutput;//截取输出流
            StreamReader error = p.StandardError;//截取错误信息                          //线程起点
            string str = reader.ReadToEnd() + error.ReadToEnd();
            p.WaitForExit();//等待程序执行完退出进程
            p.Close();
            return str;
        }
        */

        public static void task_kill (string name,int sec)
        {
            
            long t1 = get_time_stamp();
            while (true) {
                Debug.WriteLine(get_time_stamp()-t1);
                if (get_time_stamp()-t1>sec*1000 ) {
                    process_kill_by_name(name);
                    break;
                }
                Thread.Sleep(1000);
            }
                
        }

        public static long  get_time_stamp()
        {
            TimeSpan ts = DateTime.UtcNow - new DateTime(1970, 1, 1, 0, 0, 0, 0);
            return  Convert.ToInt64(ts.TotalMilliseconds);
        }

        public static void process_kill_by_name(string name)
        {
            foreach (Process p in Process.GetProcessesByName(name))
            {
                try
                {
                    ui_setlog("process_kill_by_name="+name);
                    p.Kill();
                    p.WaitForExit();
                }
                catch (Exception exp)
                {
                    Console.WriteLine(exp.Message);
                    System.Diagnostics.EventLog.WriteEntry("AlchemySearch:KillProcess", exp.Message, System.Diagnostics.EventLogEntryType.Error);
                }
            }
        }
    }

}

