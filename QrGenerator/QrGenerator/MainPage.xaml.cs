using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;
using Windows.UI.Xaml.Media.Imaging;
using ZXing;
using ZXing.Common;
using ZXing.Rendering;
using System.Threading.Tasks;
using Windows.Graphics.Imaging;
using Windows.Storage.Streams;


// La plantilla de elemento Página en blanco está documentada en https://go.microsoft.com/fwlink/?LinkId=402352&clcid=0xc0a

namespace QrGenerator
{
    /// <summary>
    /// Página vacía que se puede usar de forma independiente o a la que se puede navegar dentro de un objeto Frame.
    /// </summary>
    public sealed partial class MainPage : Page
    {
        public MainPage()
        {
            this.InitializeComponent();
        }

        private void Page_Loaded(object sender, RoutedEventArgs e)
        {

        }

        private void Generar_Click(object sender, RoutedEventArgs e)
        {
            Random random = new Random();
            int firstPart = random.Next();
            int secondPart = random.Next();
            long randomNumber = ((long)firstPart << 32) | secondPart;

            //txbLong.Text = randomNumber.ToString();

            var qrCodeImage = GenerateQRCodeAsync(randomNumber, 500, 500).Result;

            QrImage.Source = qrCodeImage;
        }

        public async Task GenerateQRCodeAsync()
        {
            var level = SelectedEcc;
            var eccLevel = (QRCodeGenerator.ECCLevel)(level == "L" ? 0 : level == "M" ? 1 : level == "Q" ? 2 : 3);
            var qrGenerator = new QRCodeGenerator();
            var qrCodeData = qrGenerator.CreateQrCode(SourceText, eccLevel);
            var qrCode = new BitmapByteQRCode(qrCodeData);
            var qrCodeImage = qrCode.GetGraphic(20);

            using (var stream = new InMemoryRandomAccessStream())
            {
                using (var writer = new DataWriter(stream.GetOutputStreamAt(0)))
                {
                    writer.WriteBytes(qrCodeImage);
                    await writer.StoreAsync();
                }
                Bitmap = new WriteableBitmap(1024, 1024);
                await Bitmap.SetSourceAsync(stream);
            }
        }

    }
}
