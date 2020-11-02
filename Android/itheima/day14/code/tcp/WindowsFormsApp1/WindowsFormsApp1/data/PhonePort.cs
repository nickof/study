using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApp1.data
{
    class PhonePort
    {
        private int port;
        public PhonePort() { }
        public PhonePort(int port){
            this.port = port;
        }

        public int getPort()
        {
            return ++port;
        }

    }
}
