namespace WindowsFormsApp1
{
    partial class Form1
    {

        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要修改
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tab1_control = new System.Windows.Forms.TabPage();
            this.button12 = new System.Windows.Forms.Button();
            this.button15 = new System.Windows.Forms.Button();
            this.button14 = new System.Windows.Forms.Button();
            this.button7 = new System.Windows.Forms.Button();
            this.button13 = new System.Windows.Forms.Button();
            this.button8 = new System.Windows.Forms.Button();
            this.panel1 = new System.Windows.Forms.Panel();
            this.Tb_apk_path = new System.Windows.Forms.TextBox();
            this.Tb_pic_path = new System.Windows.Forms.TextBox();
            this.button6 = new System.Windows.Forms.Button();
            this.button5 = new System.Windows.Forms.Button();
            this.Tb_unistall_app = new System.Windows.Forms.TextBox();
            this.button4 = new System.Windows.Forms.Button();
            this.Tb_app = new System.Windows.Forms.TextBox();
            this.button3 = new System.Windows.Forms.Button();
            this.Tb_app_activity = new System.Windows.Forms.TextBox();
            this.bt_st = new System.Windows.Forms.Button();
            this.button9 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            this.button10 = new System.Windows.Forms.Button();
            this.button11 = new System.Windows.Forms.Button();
            this.Ck_all = new System.Windows.Forms.CheckBox();
            this.button1 = new System.Windows.Forms.Button();
            this.dgvInfo = new System.Windows.Forms.DataGridView();
            this.colCheck = new System.Windows.Forms.DataGridViewCheckBoxColumn();
            this.colDevice = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.colNum = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.tableBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.tabControl1.SuspendLayout();
            this.tab1_control.SuspendLayout();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvInfo)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.tableBindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // tabControl1
            // 
            this.tabControl1.Controls.Add(this.tab1_control);
            this.tabControl1.Location = new System.Drawing.Point(2, 0);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(428, 705);
            this.tabControl1.TabIndex = 0;
            // 
            // tab1_control
            // 
            this.tab1_control.Controls.Add(this.button12);
            this.tab1_control.Controls.Add(this.button15);
            this.tab1_control.Controls.Add(this.button14);
            this.tab1_control.Controls.Add(this.button7);
            this.tab1_control.Controls.Add(this.button13);
            this.tab1_control.Controls.Add(this.button8);
            this.tab1_control.Controls.Add(this.panel1);
            this.tab1_control.Controls.Add(this.button9);
            this.tab1_control.Controls.Add(this.button2);
            this.tab1_control.Controls.Add(this.button10);
            this.tab1_control.Controls.Add(this.button11);
            this.tab1_control.Controls.Add(this.Ck_all);
            this.tab1_control.Controls.Add(this.button1);
            this.tab1_control.Controls.Add(this.dgvInfo);
            this.tab1_control.Location = new System.Drawing.Point(4, 22);
            this.tab1_control.Margin = new System.Windows.Forms.Padding(10);
            this.tab1_control.Name = "tab1_control";
            this.tab1_control.Padding = new System.Windows.Forms.Padding(3);
            this.tab1_control.Size = new System.Drawing.Size(420, 679);
            this.tab1_control.TabIndex = 0;
            this.tab1_control.Text = "主控";
            this.tab1_control.UseVisualStyleBackColor = true;
            // 
            // button12
            // 
            this.button12.Location = new System.Drawing.Point(359, 463);
            this.button12.Name = "button12";
            this.button12.Size = new System.Drawing.Size(54, 48);
            this.button12.TabIndex = 16;
            this.button12.Text = "保存";
            this.button12.UseVisualStyleBackColor = true;
            this.button12.Click += new System.EventHandler(this.button12_Click);
            // 
            // button15
            // 
            this.button15.Location = new System.Drawing.Point(258, 3);
            this.button15.Name = "button15";
            this.button15.Size = new System.Drawing.Size(75, 23);
            this.button15.TabIndex = 8;
            this.button15.Text = "实时画面";
            this.button15.UseVisualStyleBackColor = true;
            this.button15.Click += new System.EventHandler(this.button15_Click);
            // 
            // button14
            // 
            this.button14.Location = new System.Drawing.Point(109, 3);
            this.button14.Name = "button14";
            this.button14.Size = new System.Drawing.Size(75, 23);
            this.button14.TabIndex = 6;
            this.button14.Text = "启动服务";
            this.button14.UseVisualStyleBackColor = true;
            this.button14.Click += new System.EventHandler(this.button14_Click);
            // 
            // button7
            // 
            this.button7.Location = new System.Drawing.Point(229, 138);
            this.button7.Name = "button7";
            this.button7.Size = new System.Drawing.Size(89, 27);
            this.button7.TabIndex = 14;
            this.button7.Text = "关闭adb";
            this.button7.UseVisualStyleBackColor = true;
            this.button7.Click += new System.EventHandler(this.button7_Click);
            // 
            // button13
            // 
            this.button13.Location = new System.Drawing.Point(184, 3);
            this.button13.Name = "button13";
            this.button13.Size = new System.Drawing.Size(75, 23);
            this.button13.TabIndex = 5;
            this.button13.Text = "定位手机";
            this.button13.UseVisualStyleBackColor = true;
            this.button13.Click += new System.EventHandler(this.button13_Click);
            // 
            // button8
            // 
            this.button8.Location = new System.Drawing.Point(229, 111);
            this.button8.Name = "button8";
            this.button8.Size = new System.Drawing.Size(89, 27);
            this.button8.TabIndex = 13;
            this.button8.Text = "重启";
            this.button8.UseVisualStyleBackColor = true;
            this.button8.Click += new System.EventHandler(this.button8_Click);
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.Tb_apk_path);
            this.panel1.Controls.Add(this.Tb_pic_path);
            this.panel1.Controls.Add(this.button6);
            this.panel1.Controls.Add(this.button5);
            this.panel1.Controls.Add(this.Tb_unistall_app);
            this.panel1.Controls.Add(this.button4);
            this.panel1.Controls.Add(this.Tb_app);
            this.panel1.Controls.Add(this.button3);
            this.panel1.Controls.Add(this.Tb_app_activity);
            this.panel1.Controls.Add(this.bt_st);
            this.panel1.Location = new System.Drawing.Point(0, 517);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(411, 136);
            this.panel1.TabIndex = 4;
            // 
            // Tb_apk_path
            // 
            this.Tb_apk_path.AllowDrop = true;
            this.Tb_apk_path.Location = new System.Drawing.Point(89, 85);
            this.Tb_apk_path.Name = "Tb_apk_path";
            this.Tb_apk_path.Size = new System.Drawing.Size(317, 21);
            this.Tb_apk_path.TabIndex = 15;
            this.Tb_apk_path.DragDrop += new System.Windows.Forms.DragEventHandler(this.Tb_apkPath_DragDrop);
            this.Tb_apk_path.DragEnter += new System.Windows.Forms.DragEventHandler(this.Tb_apkPath_DragEnter);
            // 
            // Tb_pic_path
            // 
            this.Tb_pic_path.Location = new System.Drawing.Point(89, 112);
            this.Tb_pic_path.Name = "Tb_pic_path";
            this.Tb_pic_path.Size = new System.Drawing.Size(317, 21);
            this.Tb_pic_path.TabIndex = 9;
            // 
            // button6
            // 
            this.button6.Location = new System.Drawing.Point(0, 108);
            this.button6.Name = "button6";
            this.button6.Size = new System.Drawing.Size(89, 27);
            this.button6.TabIndex = 8;
            this.button6.Text = "导入图片";
            this.button6.UseVisualStyleBackColor = true;
            // 
            // button5
            // 
            this.button5.Location = new System.Drawing.Point(0, 81);
            this.button5.Name = "button5";
            this.button5.Size = new System.Drawing.Size(89, 27);
            this.button5.TabIndex = 6;
            this.button5.Text = "安装app";
            this.button5.UseVisualStyleBackColor = true;
            this.button5.Click += new System.EventHandler(this.button5_Click);
            // 
            // Tb_unistall_app
            // 
            this.Tb_unistall_app.Location = new System.Drawing.Point(89, 58);
            this.Tb_unistall_app.Name = "Tb_unistall_app";
            this.Tb_unistall_app.Size = new System.Drawing.Size(317, 21);
            this.Tb_unistall_app.TabIndex = 5;
            // 
            // button4
            // 
            this.button4.Location = new System.Drawing.Point(0, 54);
            this.button4.Name = "button4";
            this.button4.Size = new System.Drawing.Size(89, 27);
            this.button4.TabIndex = 4;
            this.button4.Text = "卸载app";
            this.button4.UseVisualStyleBackColor = true;
            this.button4.Click += new System.EventHandler(this.button4_Click);
            // 
            // Tb_app
            // 
            this.Tb_app.Location = new System.Drawing.Point(89, 31);
            this.Tb_app.Name = "Tb_app";
            this.Tb_app.Size = new System.Drawing.Size(317, 21);
            this.Tb_app.TabIndex = 3;
            // 
            // button3
            // 
            this.button3.Location = new System.Drawing.Point(0, 27);
            this.button3.Name = "button3";
            this.button3.Size = new System.Drawing.Size(89, 27);
            this.button3.TabIndex = 2;
            this.button3.Text = "关闭app";
            this.button3.UseVisualStyleBackColor = true;
            this.button3.Click += new System.EventHandler(this.button3_Click);
            // 
            // Tb_app_activity
            // 
            this.Tb_app_activity.Location = new System.Drawing.Point(89, 5);
            this.Tb_app_activity.Name = "Tb_app_activity";
            this.Tb_app_activity.Size = new System.Drawing.Size(317, 21);
            this.Tb_app_activity.TabIndex = 1;
            this.Tb_app_activity.Text = "com.wb/com.cyjh.elfin.activity.news.SplashActivity";
            // 
            // bt_st
            // 
            this.bt_st.Location = new System.Drawing.Point(0, 0);
            this.bt_st.Name = "bt_st";
            this.bt_st.Size = new System.Drawing.Size(89, 27);
            this.bt_st.TabIndex = 0;
            this.bt_st.Text = "启动app";
            this.bt_st.UseVisualStyleBackColor = true;
            this.bt_st.Click += new System.EventHandler(this.bt_st_Click);
            // 
            // button9
            // 
            this.button9.Location = new System.Drawing.Point(229, 84);
            this.button9.Name = "button9";
            this.button9.Size = new System.Drawing.Size(89, 27);
            this.button9.TabIndex = 12;
            this.button9.Text = "home";
            this.button9.UseVisualStyleBackColor = true;
            this.button9.Click += new System.EventHandler(this.button9_Click);
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(229, 166);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(89, 23);
            this.button2.TabIndex = 3;
            this.button2.Text = "测试";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // button10
            // 
            this.button10.Location = new System.Drawing.Point(229, 57);
            this.button10.Name = "button10";
            this.button10.Size = new System.Drawing.Size(89, 27);
            this.button10.TabIndex = 11;
            this.button10.Text = "音量-";
            this.button10.UseVisualStyleBackColor = true;
            this.button10.Click += new System.EventHandler(this.button10_Click);
            // 
            // button11
            // 
            this.button11.Location = new System.Drawing.Point(229, 30);
            this.button11.Name = "button11";
            this.button11.Size = new System.Drawing.Size(89, 27);
            this.button11.TabIndex = 10;
            this.button11.Text = "adb查询";
            this.button11.UseVisualStyleBackColor = true;
            // 
            // Ck_all
            // 
            this.Ck_all.AutoSize = true;
            this.Ck_all.Location = new System.Drawing.Point(9, 8);
            this.Ck_all.Name = "Ck_all";
            this.Ck_all.Size = new System.Drawing.Size(48, 16);
            this.Ck_all.TabIndex = 2;
            this.Ck_all.Text = "全选";
            this.Ck_all.UseVisualStyleBackColor = true;
            this.Ck_all.CheckedChanged += new System.EventHandler(this.Ck_all_CheckedChanged);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(59, 3);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(50, 23);
            this.button1.TabIndex = 1;
            this.button1.Text = "刷新";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click_1);
            // 
            // dgvInfo
            // 
            this.dgvInfo.AllowUserToAddRows = false;
            this.dgvInfo.AllowUserToDeleteRows = false;
            this.dgvInfo.AllowUserToResizeColumns = false;
            this.dgvInfo.AllowUserToResizeRows = false;
            this.dgvInfo.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.colCheck,
            this.colDevice,
            this.colNum});
            this.dgvInfo.Location = new System.Drawing.Point(0, 30);
            this.dgvInfo.Name = "dgvInfo";
            this.dgvInfo.RowTemplate.Height = 23;
            this.dgvInfo.Size = new System.Drawing.Size(223, 486);
            this.dgvInfo.TabIndex = 0;
            this.dgvInfo.CellValueChanged += new System.Windows.Forms.DataGridViewCellEventHandler(this.dgvInfo_CellValueChanged);
            // 
            // colCheck
            // 
            this.colCheck.DataPropertyName = "colCheck";
            this.colCheck.HeaderText = "选择";
            this.colCheck.Name = "colCheck";
            this.colCheck.Width = 50;
            // 
            // colDevice
            // 
            this.colDevice.DataPropertyName = "colDevice";
            this.colDevice.HeaderText = "设备";
            this.colDevice.Name = "colDevice";
            this.colDevice.ReadOnly = true;
            this.colDevice.Width = 90;
            // 
            // colNum
            // 
            this.colNum.DataPropertyName = "colNum";
            this.colNum.HeaderText = "编号";
            this.colNum.Name = "colNum";
            this.colNum.Width = 40;
            // 
            // tableBindingSource
            // 
            this.tableBindingSource.DataSource = typeof(WindowsFormsApp1.data.table);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(419, 674);
            this.Controls.Add(this.tabControl1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Form1_FormClosing);
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.Form1_FormClosed);
            this.Load += new System.EventHandler(this.Form1_Load);
            this.tabControl1.ResumeLayout(false);
            this.tab1_control.ResumeLayout(false);
            this.tab1_control.PerformLayout();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvInfo)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.tableBindingSource)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.TabPage tab1_control;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.BindingSource tableBindingSource;
        public System.Windows.Forms.DataGridView dgvInfo;
        private System.Windows.Forms.CheckBox Ck_all;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Button button7;
        private System.Windows.Forms.Button button8;
        private System.Windows.Forms.Button button9;
        private System.Windows.Forms.Button button10;
        private System.Windows.Forms.Button button11;
        private System.Windows.Forms.TextBox Tb_pic_path;
        private System.Windows.Forms.Button button6;
        private System.Windows.Forms.Button button5;
        private System.Windows.Forms.TextBox Tb_unistall_app;
        private System.Windows.Forms.Button button4;
        private System.Windows.Forms.TextBox Tb_app;
        private System.Windows.Forms.Button button3;
        private System.Windows.Forms.TextBox Tb_app_activity;
        private System.Windows.Forms.Button bt_st;
        private System.Windows.Forms.TextBox Tb_apk_path;
        public System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.Button button12;
        private System.Windows.Forms.Button button13;
        private System.Windows.Forms.Button button14;
        private System.Windows.Forms.Button button15;
        private System.Windows.Forms.DataGridViewCheckBoxColumn colCheck;
        private System.Windows.Forms.DataGridViewTextBoxColumn colDevice;
        private System.Windows.Forms.DataGridViewTextBoxColumn colNum;
    }
}

