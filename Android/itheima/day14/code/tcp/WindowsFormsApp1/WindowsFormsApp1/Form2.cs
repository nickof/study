using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;
using WindowsFormsApp1.util;
using WindowsFormsApp1.mainMethod;
using WindowsFormsApp1.data;
using System.Diagnostics;

namespace WindowsFormsApp1
{
    public partial class Form2 : Form
    {

        public Form1 form1;
        public Thread thrMain;
        public mainClass mc;
        public String adbPath;
        public String uipath;
        public static Label uilog;
        public Thread thrLog;
        public table tb;
        public List<phone> listPhones;
        public Thread thrSocket;
        TCPListener tCPListener;
        public String colorChose = "#d20a0a";
        public static List<table> listTables;
        public SortedDictionary<String, table> dicTables;
        
        private String _test;
        public String test { 
            get { 
                return _test;
            } 
            set {
                _test = value;
            }
        }

        public Form2()
        {
            InitializeComponent();
            uilog = Lb_log;
            mc = new mainClass(this);
        }

        public Form2(Form1 form1)
        {
            InitializeComponent();
            //this.form1 = form1;
        }

        public void set_log(String str)
        {
            ut.thr_down (thrLog);
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

        private void Form2_Load(object sender, EventArgs e)
        {
            //ut.process_kill_by_name("adb");
            uipath = System.Environment.CurrentDirectory + "\\ui\\set.xml";
            adbPath = mc.adbPath;
            //uiload();
            //thrDown();

            //adb_devices_set_dgv
            thrMain = new Thread(adb_devices_set_dgv);
            thrMain.IsBackground = true;
            thrMain.Start(true);


            //ut.thr_st(thrMain);

                   //ut.thr_down( thrSocket );
                    //thrSocket = new Thread( socket_run );
                    //thrSocket.Start();

            //Dgv_main.DataSource = form1.dgvInfo.DataSource;
            //ut.thr_down(thrMain);
            //thrMain = new Thread( thr_show_phone );
            //ut.thr_st( thrMain );

        }

        public void adb_devices_set_dgv(Object b) {

            listTables = ut.adb_devices();
            if (listTables != null)
            {
                Debug.WriteLine("adb_device成功..!");
                Action<List<table>> action = ( listTable ) =>{
                    Dgv_main.DataSource = listTable;
                    set_log("刷新成功");
                    listPhones= mc.show_phone(this);
                    /*if(tCPListener!=null)
                        tCPListener.flush_dicPhones(listPhones);*/
                   
                    //ut.thr_down( thrSocket );
                    //thrSocket = new Thread( socket_run );
                    //thrSocket.Start();

                  /*  if (b != null) {
                        if ((bool)b)
                            socket_run();
                    }*/
          
                };

              
               Invoke(action, listTables);

            /*    dicTables = new SortedDictionary<string, table>();
                foreach (table tb  in listTables)
                {
                    dicTables[tb.colDevice] = tb;
                }*/

                foreach (phone ph in listPhones)
                {
                    ph.Pbx_phone.Click += phone_click;
                }

            }
            else MessageBox.Show("刷新失敗,可能adb未正常啟動");
        }


        private void phone_click(object sender, EventArgs e)
        {
            PictureBox p=  (PictureBox)sender;
            phone p2= (phone)p.GetContainerControl();

            if ((Control.ModifierKeys & Keys.Shift) == Keys.Shift)
            {

                if (p2.BackColor == ColorTranslator.FromHtml("#d20a0a"))
                {
                    p2.BackColor = Color.Black;
                    //dicTables[p2.colDevice].colCheck = false;
                    listTables.Find(t => t.colDevice.Contains(p2.colDevice.Trim())).colCheck=false ;
                    listTables.Sort();

                    foreach (table tb in listTables) {
                        Debug.Print( tb.ToString() );
                    }

                    Dgv_main.Invoke(new Action(()=> {
                        Dgv_main.DataSource = listTables;
                        Dgv_main.Refresh();
                    } ));

                }
                else
                {
                    p2.BackColor = ColorTranslator.FromHtml("#d20a0a");
                    listTables.Find(t => t.colDevice.Contains(p2.colDevice.Trim())).colCheck = true;
                    listTables.Sort();
                    foreach (table tb in listTables)
                    {
                        Debug.Print(tb.ToString());
                    }
                    Dgv_main.Invoke(new Action(() =>
                    {
                        Dgv_main.DataSource = listTables;
                        Dgv_main.Refresh();
                    }));
                }

            }

        }

        public Color set_color(String col) { 
            return ColorTranslator.FromHtml(col);
        }

        public void thr_show_phone() {
            mc.show_phone(this);
        }

        private void Cb_check_CheckedChanged(object sender, EventArgs e)
        {
            ut.thr_down(thrMain);
            thrMain = new Thread(() =>
            {
                Action action = () =>
                {
                    bool b;
                    //遍历datagridview中的每一行，判断是否选中，若为选中，则选中
                    for (int i = 0; i < Dgv_main.Rows.Count; i++)
                    {
                        b =Cb_check.Checked;
                        Dgv_main.Rows[i].Cells["colCheck"].Value = b;
                    }
                };
                Invoke(action);
            });
            ut.thr_st(thrMain);
        }

        private void Form2_FormClosed(object sender, FormClosedEventArgs e)
        {
            System.Environment.Exit(0);
        }

        private void Form2_FormClosing(object sender, FormClosingEventArgs e)
        {
          
        }

        private void button2_Click(object sender, EventArgs e)
        {
            //Action a = form1.start_service();
            //thrDown();
            //thrMain = new Thread(new ParameterizedThreadStart(get_checked_dgv_all_run));
            //thrMain.Start(a);

            Action a = start_service;
            ut.thr_down( thrMain );
            thrMain = new Thread(new ParameterizedThreadStart(get_checked_dgv_all_run));
            thrMain.Start(a);


            //ut.thr_down(thrSocket);
            //thrSocket = new Thread(socket_run);
            //thrSocket.Start();

            //ut.thr_down(thrMain);
            //thrMain = new Thread( thr_show_phone );
            //ut.thr_st( thrMain );

        }

        public void get_checked_dgv_all_run(Object a)
        {
            Action b = (Action)a;
          List<table> tables = get_check_dgv_all();
            foreach (table table in tables)
            {
                tb = table;
                b();
            }
            ut.ui_setlog("get_checked_dgv_all_run::finish");
        }

        public void socket_run()
        {
            tCPListener = new TCPListener(this, listPhones);
            tCPListener.Init();
        }

        public List<table> get_check_dgv_all()
        {

            List<table> list = new List<table>();
            for (int i = 0; i < Dgv_main.Rows.Count; i++)
            {
                if (Convert.ToBoolean(Dgv_main.Rows[i].Cells["colCheck"].Value) == true)
                {
                    list.Add(new table(
                        Convert.ToBoolean(Dgv_main.Rows[i].Cells["colCheck"].Value),
                        Dgv_main.Rows[i].Cells["colDevice"].Value.ToString(),
                        Dgv_main.Rows[i].Cells["colNum"].Value.ToString()
                        ));
                }
            }
            return list;

        }

        public void start_service()
        {
            //+tb.colDevice + " shell am startservice -n com.example.adbtcp/com.example.adbtcp.ServiceScreen"
            ut.cmd(@"shell am startservice -n com.example.adbtcp/com.example.adbtcp.MyService",
            adbPath, tb.colDevice);
        }

        private void button3_Click(object sender, EventArgs e)
        {

            //ut.process_kill_by_name("adb");
            uipath = System.Environment.CurrentDirectory + "\\ui\\set.xml";
            adbPath = mc.adbPath;
            //uiload();
            //thrDown();

            thrMain = new Thread( adb_devices_set_dgv );
            ut.thr_st(thrMain);

        }

        private void button1_Click(object sender, EventArgs e)
        {
            new Form1(this).Show();
        }

        private void Tab_main_ControlAdded(object sender, ControlEventArgs e)
        {

        }

        private void button4_Click(object sender, EventArgs e)
        {
            //int port = Convert.ToInt32( Tb_test.Text);
            //if (ut.port_in_use(port, ut.EportType.TCP)) ;
            //    MessageBox.Show(port + "use");
            //else
            //    MessageBox.Show(port + "noUse");
            //new TCPListener(this).socketClient(8001);

            //Thread thr = new Thread(test2);
            //thr.IsBackground = true;
            //thr.Start();
            new TCPListener(this).socketClient(8001);

        }

        public void test2() {
            new TCPListener(this).socketClient(8001);
        }

        private void groupBox1_Enter(object sender, EventArgs e)
        {

        }

        private void button5_Click(object sender, EventArgs e)
        {
            foreach( var tp in listPhones)
            {
                new TCPListener(tp).socketClient();
            }
        }
    }
}
