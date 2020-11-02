namespace WindowsFormsApp1
{
    partial class phone
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

        #region 组件设计器生成的代码

        /// <summary> 
        /// 设计器支持所需的方法 - 不要修改
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            this.Pbx_phone = new System.Windows.Forms.PictureBox();
            this.Lb_bottom = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.Pbx_phone)).BeginInit();
            this.SuspendLayout();
            // 
            // Pbx_phone
            // 
            this.Pbx_phone.BackColor = System.Drawing.Color.Transparent;
            this.Pbx_phone.Location = new System.Drawing.Point(0, 0);
            this.Pbx_phone.Name = "Pbx_phone";
            this.Pbx_phone.Size = new System.Drawing.Size(170, 240);
            this.Pbx_phone.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.Pbx_phone.TabIndex = 0;
            this.Pbx_phone.TabStop = false;
            // 
            // Lb_bottom
            // 
            this.Lb_bottom.BackColor = System.Drawing.Color.Black;
            this.Lb_bottom.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.Lb_bottom.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Lb_bottom.ForeColor = System.Drawing.Color.White;
            this.Lb_bottom.Location = new System.Drawing.Point(0, 243);
            this.Lb_bottom.Name = "Lb_bottom";
            this.Lb_bottom.Size = new System.Drawing.Size(170, 22);
            this.Lb_bottom.TabIndex = 2;
            this.Lb_bottom.Text = "Lb_bottom";
            this.Lb_bottom.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // phone
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.ControlText;
            this.Controls.Add(this.Lb_bottom);
            this.Controls.Add(this.Pbx_phone);
            this.Name = "phone";
            this.Size = new System.Drawing.Size(170, 265);
            this.AutoSizeChanged += new System.EventHandler(this.phone_AutoSizeChanged);
            this.Load += new System.EventHandler(this.phone_Load);
            ((System.ComponentModel.ISupportInitialize)(this.Pbx_phone)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.Label Lb_bottom;
        public System.Windows.Forms.PictureBox Pbx_phone;
    }
}
