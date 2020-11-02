using System.Drawing;

namespace WindowsFormsApp1
{
    partial class Form2
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        /// 
        private System.ComponentModel.IContainer components = null;
        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.Tab_main = new System.Windows.Forms.TabControl();
            this.Tpg_main = new System.Windows.Forms.TabPage();
            this.Gr_2 = new System.Windows.Forms.GroupBox();
            this.Cb_check = new System.Windows.Forms.CheckBox();
            this.Dgv_main = new System.Windows.Forms.DataGridView();
            this.colCheck = new System.Windows.Forms.DataGridViewCheckBoxColumn();
            this.colDevice = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.colNum = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.Tb_test = new System.Windows.Forms.TextBox();
            this.button4 = new System.Windows.Forms.Button();
            this.button3 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            this.button1 = new System.Windows.Forms.Button();
            this.Lb_log = new System.Windows.Forms.Label();
            this.button5 = new System.Windows.Forms.Button();
            this.tabControl1.SuspendLayout();
            this.tabPage1.SuspendLayout();
            this.Tab_main.SuspendLayout();
            this.Gr_2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.Dgv_main)).BeginInit();
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // tabControl1
            // 
            this.tabControl1.Controls.Add(this.tabPage1);
            this.tabControl1.Location = new System.Drawing.Point(0, 32);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(1892, 943);
            this.tabControl1.TabIndex = 0;
            // 
            // tabPage1
            // 
            this.tabPage1.AutoScroll = true;
            this.tabPage1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(242)))), ((int)(((byte)(242)))), ((int)(((byte)(241)))));
            this.tabPage1.Controls.Add(this.groupBox2);
            this.tabPage1.Controls.Add(this.Tab_main);
            this.tabPage1.Controls.Add(this.Gr_2);
            this.tabPage1.Controls.Add(this.groupBox1);
            this.tabPage1.Location = new System.Drawing.Point(4, 22);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(1884, 917);
            this.tabPage1.TabIndex = 0;
            // 
            // groupBox2
            // 
            this.groupBox2.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(242)))), ((int)(((byte)(242)))), ((int)(((byte)(241)))));
            this.groupBox2.Location = new System.Drawing.Point(9, 630);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(213, 276);
            this.groupBox2.TabIndex = 3;
            this.groupBox2.TabStop = false;
            // 
            // Tab_main
            // 
            this.Tab_main.Controls.Add(this.Tpg_main);
            this.Tab_main.DrawMode = System.Windows.Forms.TabDrawMode.OwnerDrawFixed;
            this.Tab_main.Location = new System.Drawing.Point(228, 61);
            this.Tab_main.Name = "Tab_main";
            this.Tab_main.SelectedIndex = 0;
            this.Tab_main.Size = new System.Drawing.Size(1639, 886);
            this.Tab_main.TabIndex = 2;
            this.Tab_main.ControlAdded += new System.Windows.Forms.ControlEventHandler(this.Tab_main_ControlAdded);
            // 
            // Tpg_main
            // 
            this.Tpg_main.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(247)))), ((int)(((byte)(248)))), ((int)(((byte)(250)))));
            this.Tpg_main.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(247)))), ((int)(((byte)(248)))), ((int)(((byte)(250)))));
            this.Tpg_main.Location = new System.Drawing.Point(4, 22);
            this.Tpg_main.Name = "Tpg_main";
            this.Tpg_main.Padding = new System.Windows.Forms.Padding(3);
            this.Tpg_main.Size = new System.Drawing.Size(1631, 860);
            this.Tpg_main.TabIndex = 0;
            this.Tpg_main.Text = "View";
            // 
            // Gr_2
            // 
            this.Gr_2.Controls.Add(this.Cb_check);
            this.Gr_2.Controls.Add(this.Dgv_main);
            this.Gr_2.Location = new System.Drawing.Point(3, 56);
            this.Gr_2.Name = "Gr_2";
            this.Gr_2.Size = new System.Drawing.Size(219, 571);
            this.Gr_2.TabIndex = 1;
            this.Gr_2.TabStop = false;
            // 
            // Cb_check
            // 
            this.Cb_check.Location = new System.Drawing.Point(5, 19);
            this.Cb_check.Name = "Cb_check";
            this.Cb_check.Size = new System.Drawing.Size(69, 27);
            this.Cb_check.TabIndex = 0;
            this.Cb_check.Text = "全选";
            this.Cb_check.UseVisualStyleBackColor = true;
            this.Cb_check.CheckedChanged += new System.EventHandler(this.Cb_check_CheckedChanged);
            // 
            // Dgv_main
            // 
            this.Dgv_main.AllowUserToAddRows = false;
            this.Dgv_main.AllowUserToDeleteRows = false;
            this.Dgv_main.BackgroundColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.Dgv_main.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.Dgv_main.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.colCheck,
            this.colDevice,
            this.colNum});
            this.Dgv_main.Dock = System.Windows.Forms.DockStyle.Fill;
            this.Dgv_main.GridColor = System.Drawing.SystemColors.ButtonHighlight;
            this.Dgv_main.Location = new System.Drawing.Point(3, 17);
            this.Dgv_main.Name = "Dgv_main";
            this.Dgv_main.RowTemplate.Height = 23;
            this.Dgv_main.Size = new System.Drawing.Size(213, 551);
            this.Dgv_main.TabIndex = 0;
            // 
            // colCheck
            // 
            this.colCheck.DataPropertyName = "colCheck";
            this.colCheck.HeaderText = "";
            this.colCheck.Name = "colCheck";
            this.colCheck.Width = 30;
            // 
            // colDevice
            // 
            this.colDevice.DataPropertyName = "colDevice";
            this.colDevice.HeaderText = "序列号";
            this.colDevice.Name = "colDevice";
            this.colDevice.ReadOnly = true;
            // 
            // colNum
            // 
            this.colNum.DataPropertyName = "colNum";
            this.colNum.HeaderText = "编号";
            this.colNum.Name = "colNum";
            this.colNum.ReadOnly = true;
            this.colNum.Width = 40;
            // 
            // groupBox1
            // 
            this.groupBox1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(242)))), ((int)(((byte)(242)))), ((int)(((byte)(241)))));
            this.groupBox1.Controls.Add(this.button5);
            this.groupBox1.Controls.Add(this.Tb_test);
            this.groupBox1.Controls.Add(this.button4);
            this.groupBox1.Controls.Add(this.button3);
            this.groupBox1.Controls.Add(this.button2);
            this.groupBox1.Controls.Add(this.button1);
            this.groupBox1.Location = new System.Drawing.Point(3, 0);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(1879, 55);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            this.groupBox1.Enter += new System.EventHandler(this.groupBox1_Enter);
            // 
            // Tb_test
            // 
            this.Tb_test.Location = new System.Drawing.Point(499, 10);
            this.Tb_test.Name = "Tb_test";
            this.Tb_test.Size = new System.Drawing.Size(100, 21);
            this.Tb_test.TabIndex = 4;
            // 
            // button4
            // 
            this.button4.Location = new System.Drawing.Point(406, 10);
            this.button4.Name = "button4";
            this.button4.Size = new System.Drawing.Size(75, 23);
            this.button4.TabIndex = 3;
            this.button4.Text = "test";
            this.button4.UseVisualStyleBackColor = true;
            this.button4.Click += new System.EventHandler(this.button4_Click);
            // 
            // button3
            // 
            this.button3.Location = new System.Drawing.Point(155, 10);
            this.button3.Name = "button3";
            this.button3.Size = new System.Drawing.Size(74, 40);
            this.button3.TabIndex = 2;
            this.button3.Text = "刷新";
            this.button3.UseVisualStyleBackColor = true;
            this.button3.Click += new System.EventHandler(this.button3_Click);
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(6, 10);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(74, 40);
            this.button2.TabIndex = 1;
            this.button2.Text = "启动服务";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // button1
            // 
            this.button1.FlatAppearance.BorderSize = 0;
            this.button1.Location = new System.Drawing.Point(80, 10);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(74, 40);
            this.button1.TabIndex = 0;
            this.button1.Text = "常用操作";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // Lb_log
            // 
            this.Lb_log.BackColor = System.Drawing.SystemColors.ActiveCaption;
            this.Lb_log.Font = new System.Drawing.Font("宋体", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Lb_log.ForeColor = System.Drawing.Color.Chartreuse;
            this.Lb_log.Location = new System.Drawing.Point(6, 1);
            this.Lb_log.Name = "Lb_log";
            this.Lb_log.Size = new System.Drawing.Size(1180, 28);
            this.Lb_log.TabIndex = 5;
            this.Lb_log.Text = "..";
            // 
            // button5
            // 
            this.button5.Location = new System.Drawing.Point(229, 10);
            this.button5.Name = "button5";
            this.button5.Size = new System.Drawing.Size(74, 40);
            this.button5.TabIndex = 3;
            this.button5.Text = "实时画面";
            this.button5.UseVisualStyleBackColor = true;
            this.button5.Click += new System.EventHandler(this.button5_Click);
            // 
            // Form2
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(1892, 973);
            this.Controls.Add(this.Lb_log);
            this.Controls.Add(this.tabControl1);
            this.Name = "Form2";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "主控";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Form2_FormClosing);
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.Form2_FormClosed);
            this.Load += new System.EventHandler(this.Form2_Load);
            this.tabControl1.ResumeLayout(false);
            this.tabPage1.ResumeLayout(false);
            this.Tab_main.ResumeLayout(false);
            this.Gr_2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.Dgv_main)).EndInit();
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.GroupBox Gr_2;
        private System.Windows.Forms.TabPage Tpg_main;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.DataGridViewCheckBoxColumn colCheck;
        private System.Windows.Forms.DataGridViewTextBoxColumn colDevice;
        private System.Windows.Forms.DataGridViewTextBoxColumn colNum;
        private System.Windows.Forms.CheckBox Cb_check;
        public System.Windows.Forms.DataGridView Dgv_main;
        public System.Windows.Forms.TabControl Tab_main;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button button2;
        public System.Windows.Forms.Label Lb_log;
        private System.Windows.Forms.Button button3;
        private System.Windows.Forms.Button button4;
        private System.Windows.Forms.TextBox Tb_test;
        private System.Windows.Forms.Button button5;
    }
}