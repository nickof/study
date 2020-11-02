using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using WindowsFormsApp1.data;
using WindowsFormsApp1.util;

namespace WindowsFormsApp1
{

    public partial class phone : UserControl, IEquatable<phone>, IComparable<phone>
    {

        public String imgWidth;
        public String LbBottomWidth;
        public String imgHeight;
        public String LbBottomHeight;

        public phone()
        {
            InitializeComponent();
        }

        [Browsable(true)]
        [Description("imgPath"), Category("自定义")]
        private String _imgPath;
        public String imgPath { get {
                return _imgPath;
            }
            set {
                Pbx_phone.ImageLocation = value;
            }
        }

        [Browsable(true)]
        [Description("父窗体width"), Category("自定义")]
        private int _paWidth;
        public int paWidth { get {
                return _paWidth;
            }
            set {
                this.Width = value ;
            }
        }

        [Browsable(true)]
        [Description("父窗体height"), Category("自定义")]
        private int _paHeight;
        public int paHeight
        {
            get
            {
                return _paHeight;
            }
            set
            {
                this.Height = value;
            }
        }

        [Browsable(true)]
        [Description("底部标签text"),Category("自定义")]
        private string _lbText;
        public string lbText { 
            get => _lbText;
            set
            {
                _lbText = value;
                Lb_bottom.Text = value;
            }
        }

        public string colDevice
        {
            get => _colDevice;
            set
            {
                _colDevice = value;
                pathPc = System.Environment.CurrentDirectory + "\\phone\\" + _colDevice;
            }
        }

        private int _port;
        public string pathPc { get => _pathPc; set => _pathPc = value; }
        public int Port { get => _port; set => _port = value; }

        private String _colDevice;
        private String _pathPc;

        private void phone_AutoSizeChanged(object sender, EventArgs e)
        {
            Pbx_phone.Height = this.Height - 22;
        }

        public int CompareTo(phone other)
        {
            if (other == null)
                return 1;
            else
                return this.lbText.CompareTo(other.lbText);
        }
        public bool Equals(phone other)
        {
            if (other == null) return false;
            else return Equals(other);
        }

        private void phone_Load(object sender, EventArgs e)
        {
            TCPListener tcp = new TCPListener(this);
            
        }
    }

    
}
