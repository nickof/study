using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Net;
using System.Net.Sockets;
using System.Text;
using WindowsFormsApp1.mainMethod;
using WindowsFormsApp1.data;
using WindowsFormsApp1.util;
using System.Diagnostics;
using System.Threading;

namespace WindowsFormsApp1
{

    public partial class Form1 : Form
    {
        public Thread thrMain;
        public Thread thrLog;
        public Form1 form1;
        public Form2 form2;
        public delegate void degFresh(List<table> t);
        public String adbPath;
        public mainClass mc;
        public String strLog;
        public delegate String deg_adb(String device,String needValue);
        public static Label uilog;
        public static String uipath;
        public List<table> tables;
        public table tb;

        public void iiiiinit() { }
        public Form1()
        {
            InitializeComponent();
            form1 = this;
            // ml = new mainLogic(this);
            thrMain = null;
            //mc = new mainClass(this);
            //uilog = Lb_log;
        }

        public Form1( Form2 form2 )
        {
            InitializeComponent();
            form1 = this;
            //ml = new mainLogic(this);
            thrMain = null;
            this.form2 = form2;
            adbPath = ut.get_adb_path();
            //mc = new mainClass(this);
            //uilog = Lb_log;
        }


        public void set_log(String str)
        {
            thrDown(thrLog);
            thrLog = new Thread(() =>
            {
                Action<String> action = (data) =>
                {
                    Form2.uilog.Text = data.ToString() + ":" + DateTime.Now.ToLongTimeString().ToString();
                };
                Invoke(action, str);
            });
            thrLog.Start();
        }

        public void adb_refresh()
        {
            //mc = new mainClass(this);
            List<table> list = mc.fresh_adb_run();
            if (list != null)
            {
                degFresh deg_set_devinfo = set_devinfo;
                Invoke( deg_set_devinfo, list );
            }
            else MessageBox.Show("刷新失敗,可能adb未正常啟動");

        }

        public void set_devinfo(List<table> list)
        {
            dgvInfo.DataSource = list;
        }

        private void button1_Click_1(object sender, EventArgs e)
        {
            thrDown();
            thrMain = new Thread(adb_refresh);
            thrSt(thrMain);
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


        private void Form1_FormClosed(object sender, FormClosedEventArgs e)
        {
            //System.Environment.Exit(0);
            ut.thr_down(thrMain);
            //this.Close();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

            //ut.process_kill_by_name("adb");
            mc = new mainClass(this);
            uipath = System.Environment.CurrentDirectory+"\\ui\\set.xml";
            //adbPath = mc.get_adb_path();
            uiload();
            dgvInfo.DataSource = form2.Dgv_main.DataSource;

            //thrDown();
            //thrMain = new Thread(adb_refresh);
            //thrSt( thrMain );

        }

        public void uiload(){

            Tb_app_activity.Text = ut.get_xml_value(uipath, "Tb_app_activity");
            Tb_app.Text = ut.get_xml_value(uipath, "Tb_app");
            Tb_unistall_app.Text = ut.get_xml_value(uipath, "Tb_unistall_app");
            Tb_apk_path.Text = ut.get_xml_value(uipath, "Tb_apk_path");
            Tb_pic_path .Text = ut.get_xml_value(uipath, "Tb_app_activity");

        }

        private void Ck_all_CheckedChanged(object sender, EventArgs e)
        {
            thrDown();
            thrMain = new Thread(() =>
            {
                Action action = () =>
                {
                    bool b;
                    //遍历datagridview中的每一行，判断是否选中，若为选中，则选中
                    for (int i = 0; i < dgvInfo.Rows.Count; i++)
                    {
                        b = Ck_all.Checked;
                        dgvInfo.Rows[i].Cells["colCheck"].Value = b;
                    }
                };
                Invoke(action);
            });
            thrMain.Start();
        }


        private void button2_Click(object sender, EventArgs e)
        {
            List<table> list = new List<table>();

            list.Add(new table(false, "abcd", "22"));
            list.Add(new table(false, "abcd", "33"));
            list.Add(new table(false, "abcd", "1"));

            //new phone().Show();

            /*        var tsByte = new byte[] { 25, 30, 21, 255, 25, 52, 65, 78, 255 };
                    var a = tsByte.Take(4).ToArray();
                    var b = tsByte.Skip(4).ToArray();
                    Debug.Print("oik");*/

            Debug.WriteLine("\nBefore sort:");
            foreach (table aPart in list)
            {
                Console.WriteLine(aPart);
            }
            list.Sort();

            Console.WriteLine("\nAfter sort by part number:");
            foreach (table aPart in list)
            {
                Console.WriteLine(aPart);
            }

            //Form2 aa = new Form2(this);
            //aa.Show();
            // new TCPListener(aa).Init();

        }



        public void mmmmain() { }
        
        public void adb_reboot()
        {
            String cmdStr = "reboot";
            ut.cmd( cmdStr, adbPath, get_dgv_all() );
        }
        public void adb_keypress_vol()
        {
            String[] cmdStr =new string[] { @"
            shell sendevent /dev/input/event6 0001 0114 00000001",
            "shell sendevent /dev/input/event6 0000 0000 00000000",
            "shell sendevent /dev/input/event6 0001 0114 00000000",
            "shell sendevent /dev/input/event6 0000 0000 00000000",
            "shell sendevent /dev/input/event6 0001 0114 00000000",
            "shell sendevent /dev/input/event6 0000 0000 00000000",
           };
            ut.cmd(cmdStr, adbPath, get_dgv_all());
            //ut.cmd(cmdStr, adbPath, get_dgv_all());
        }

        public void adb_keypress_home()
        {
            String cmdStr = "shell input keyevent 3" ;
            ut.cmd ( cmdStr, adbPath, get_dgv_all() );
        }

        public void adb_unistall()
        {
            String cmdStr = "uninstall " + Tb_unistall_app.Text;
            ut.cmd(cmdStr, adbPath, get_dgv_all());
        }

        public void adb_install()
        {
            String cmdStr = "install -r " + Tb_apk_path.Text;
            ut.cmd(cmdStr, adbPath, get_dgv_all());
        }

        /// <summary>
        /// 获取dgv所有device
        /// </summary>
        /// <returns></returns>
        public List<String> get_dgv_all()
        {
            List<String> list = new List<string>();
            for (int i = 0; i < dgvInfo.Rows.Count; i++)
            {
                if (Convert.ToBoolean(dgvInfo.Rows[i].Cells["colCheck"].Value) == true)
                {
                    list.Add(dgvInfo.Rows[i].Cells["colDevice"].Value.ToString());
                    Debug.Print("get_dgv_all.." + dgvInfo.Rows[i].Cells["colDevice"].Value.ToString());
                }
            }
            return list;
        }

        public List<table> get_check_dgv_all()
        {
            List<table> list = new List<table>();
            for (int i = 0; i < dgvInfo.Rows.Count; i++)
            {
                if (Convert.ToBoolean(dgvInfo.Rows[i].Cells["colCheck"].Value) == true)
                {
                    list.Add(new table(
                        Convert.ToBoolean(dgvInfo.Rows[i].Cells["colCheck"].Value),
                        dgvInfo.Rows[i].Cells["colDevice"].Value.ToString(),
                        dgvInfo.Rows[i].Cells["colNum"].Value.ToString()
                        ) );
                }
            }
            return list;
        }

        public void run_all_checked(String value,deg_adb deg_cmd_run)
        {
            List<String> list = new List<string>();
            String ret;
            for (int i = 0; i < dgvInfo.Rows.Count; i++)
            {
                if (Convert.ToBoolean(dgvInfo.Rows[i].Cells[0].Value))
                {
                    ret=deg_cmd_run(dgvInfo.Rows[i].Cells[1].Value.ToString(),value);
                }
            }
        }

        /// <summary>
        /// 执行start Activity
        /// </summary>
        /// <param name="device"></param>
        /// <param name="value"></param>      

        public void adb_start(String device,String value ) {
            String cmdStr = "shell am start -n " + value;
            ut.cmd(cmdStr, adbPath, device);
        }
  
        public String adb_start(List<String> deviceList, String value)
        {
            String cmdStr = "shell am start -n " + value;
            ut.cmd(cmdStr, adbPath, deviceList);
            return "";
        }


        public void adb_start()
        {
            adb_start(get_dgv_all(),Tb_app_activity.Text);
        }

        public void adb_kill(){ adb_kill(get_dgv_all(), Tb_app.Text); }
        public void adb_kill(List<String> deviceList, String value)
        {
            String cmdStr = "shell am force-stop " + value;
            ut.cmd(cmdStr, adbPath, deviceList);
        }

        private void bt_st_Click(object sender, EventArgs e)
        {
            thrDown();
            thrMain = new Thread(adb_start);
            thrMain.Start();
           //adb_start(get_dgv_all(), "com.wb/com.cyjh.elfin.activity.news.SplashActivity");
            // run_all_checked("com.mempmjmbmfmm.mysl/com.cyjh.elfin.activity.SplashActivity", adb_start);
        }

        //关闭app
        private void button3_Click(object sender, EventArgs e)
        {
            thrDown();
            thrMain = new Thread(adb_kill);
            thrMain.Start();
        }
        
        //安装
        private void button5_Click(object sender, EventArgs e)
        {
            thrDown();
            thrMain = new Thread(adb_install);
            thrMain.Start();
        }

        private void Tb_apkPath_DragEnter(object sender, DragEventArgs e)
        {
            if (e.Data.GetDataPresent(DataFormats.FileDrop))
                e.Effect = DragDropEffects.All;                                                              //重要代码：表明是所有类型的数据，比如文件路径
            else
                e.Effect = DragDropEffects.None;
        }

        private void Tb_apkPath_DragDrop(object sender, DragEventArgs e)
        {
            string path = ((System.Array)e.Data.GetData(DataFormats.FileDrop)).GetValue(0).ToString();       //获得路径
            Tb_apk_path.Text = path;
        }

        private void button4_Click(object sender, EventArgs e)
        {
            thrDown();
            thrMain = new Thread(adb_unistall);
            thrMain.Start();
        }

        private void button9_Click(object sender, EventArgs e)
        {
            thrDown();
            thrMain = new Thread( adb_keypress_home );
            thrMain.Start();
        }

        private void button7_Click(object sender, EventArgs e)
        {
            thrDown();
            thrMain = new Thread( ()=> {
                ut.process_kill_by_name("adb");
            } );
            thrMain.Start();
        }

        private void button10_Click(object sender, EventArgs e)
        {
            thrDown();
            thrMain = new Thread(adb_keypress_vol);
            thrMain.Start();
        }

        private void button8_Click(object sender, EventArgs e)
        {
            if ((int)MessageBox.Show("选中的手机将会重启", "警告", MessageBoxButtons.OKCancel, MessageBoxIcon.Exclamation) != 1) { return; }
            thrDown();
            thrMain = new Thread(adb_reboot);
            thrMain.Start();
        }

        private void dgvInfo_CellValueChanged(object sender, DataGridViewCellEventArgs e)
        {
            String path = System.Environment.CurrentDirectory + "\\numSetting\\";
            String value = "";
            Debug.WriteLine( e.RowIndex.ToString()+","+ e.ColumnIndex.ToString());
       
            if (e.RowIndex > -1 && e.ColumnIndex == 0){
                path = path + dgvInfo.Rows[e.RowIndex].Cells["colDevice"].Value.ToString() + ".txt";
                value = dgvInfo.Rows[e.RowIndex].Cells[e.ColumnIndex].Value.ToString();
                ut.file_write(path,value);
            }
            //Debug.Print(dgvInfo.Rows[e.RowIndex].Cells[e.ColumnIndex].Value.ToString());      
            //MessageBox.Show(e.RowIndex.ToString() + "," + e.ColumnIndex.ToString() ); 
        }

        private void button12_Click(object sender, EventArgs e)
        {
            Dictionary<String, String> dic = new  Dictionary<string, string>() ;
            dic.Add("Tb_app_activity", Tb_app_activity.Text);
            dic.Add("Tb_app", Tb_app.Text);
            dic.Add("Tb_unistall_app", Tb_unistall_app.Text);
            dic.Add("Tb_apk_path", Tb_apk_path.Text);
            dic.Add("Tb_pic_path", Tb_pic_path.Text);

            String path = System.Environment.CurrentDirectory + "\\ui\\set.xml";
            ut.set_xml_value(path, dic);
            ut.ui_setlog("保存完成");

        }

        

        private void button13_Click(object sender, EventArgs e)
        {
            //ut.cmd()
            //mc.socketSend("find,30", 8000);
            //ut.cmd("", adbPath);

            Action a = find_phone;
            thrDown();
            thrMain = new Thread(new ParameterizedThreadStart(get_checked_dgv_all_run) );
            thrMain.Start(a);
        
        }

        private void button14_Click(object sender, EventArgs e)
        {
            Action a = start_service;
            thrDown();
            thrMain = new Thread(new ParameterizedThreadStart(get_checked_dgv_all_run));
            thrMain.Start(a);
        }

        public void start_service() {
            //+tb.colDevice + " shell am startservice -n com.example.adbtcp/com.example.adbtcp.ServiceScreen"
            ut.cmd (@"shell am startservice -n com.example.adbtcp/com.example.adbtcp.MyService", 
            adbPath, tb.colDevice);
        }

        public void start_screen_service() {
            ut.cmd_no_return("shell am startservice -n com.example.adbtcp/com.example.adbtcp.ServiceScreen", adbPath, tb.colDevice);
        }

        public void find_phone() {
            ut.cmd("forward tcp:8000 tcp:9000", adbPath, tb.colDevice);
            mc.socketSend("find," + tb.colNum);
        }



        public void get_checked_dgv_all_run(Object a){
            Action b =(Action)a ;
            tables = get_check_dgv_all();
            foreach ( table table in tables ) {
                tb = table;
                b();
            }
            ut.ui_setlog("get_checked_dgv_all_run::finish");
        }

        private void button15_Click(object sender, EventArgs e)
        {
            
        }

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {

        }
    }
}
