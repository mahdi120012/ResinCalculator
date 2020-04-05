package com.e.resincalculator

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.github.javiersantos.appupdater.AppUpdater
import com.github.javiersantos.appupdater.enums.UpdateFrom
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import com.rbddevs.splashy.Splashy
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.customdialog.*
import kotlinx.android.synthetic.main.navigation_items.*
import kotlinx.android.synthetic.main.navigation_main_activity.*
import kotlinx.android.synthetic.main.toolbar_top.*
import java.text.DecimalFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private var rAdapter:RecyclerAdapter? = null
    private var rModels:ArrayList<RecyclerModel>? = null
    var geram = 0.0
    var kilograms = 0.0
    var pound = 0.0
    var ounce = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_main_activity)

        setSplashy()

        //line zir baraye update automatic ee.
        val appUpdater = AppUpdater(this).setUpdateFrom(UpdateFrom.JSON)
            .setUpdateJSON("http://robika.ir/ultitled/practice/resin_epoxy_update_checker.json")
            .setTitleOnUpdateAvailable("بروزرسانی جدید موجوده!").setButtonUpdate("بروزرسانی")
            .setButtonDismiss("فعلا نه").setButtonDoNotShowAgain("")
        appUpdater.start()


        imgNavigationTop.setOnClickListener{
            if (drawer_layout.isDrawerOpen(Gravity.LEFT)) {
                drawer_layout.closeDrawer(Gravity.LEFT)
            } else {
                drawer_layout.openDrawer(Gravity.LEFT)
            }
        }

        txAboutMe.setOnClickListener{
            if (drawer_layout.isDrawerOpen(Gravity.LEFT)) {
                drawer_layout.closeDrawer(Gravity.LEFT)
            }

            val inflater =
                getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val customView: View = inflater.inflate(R.layout.customdialog, null)

            MaterialStyledDialog.Builder(this)
                .setCustomView(customView)
                .setHeaderDrawable(R.drawable.ic_phonelink_black_24dp)
                .setHeaderScaleType(ImageView.ScaleType.FIT_CENTER)
                .setHeaderColorInt(Color.WHITE)
                .withDialogAnimation(true)
                .show()
        }


        txReset.setOnClickListener{

            etErtefa.setText("")
            etToolB.setText("")
            etGhotrAndArzA.setText("")
            etTarakomEpoxy.setText("1.13")
            etDarsadTaghsim.setText("50")
            etDarsadKol.setText("100")
            textView11.setText("0")
            txLitaaar.setText("0")
            txHarder.setText("")
            txResin.setText("")
            drawer_layout.closeDrawer(Gravity.LEFT)
        }


        txExit.setOnClickListener{
            finish()
        }

        nav_footer_txVesionCode.text = "نسخه " + EnglishNumberToPersian().convert(AppVersionName.getVersionName(this))


        //Toast.makeText(this,Math.PI.toString(),Toast.LENGTH_LONG).show()

        //GET Information Catigury Mahsolat:
        //rModels = ArrayList()
        //rAdapter = RecyclerAdapter(rModels,"add_main",this@MainActivity, rAdapter)

        //Recyclerview.define_recyclerviewYh(this, rvVideo, rAdapter, rModels, null )

        //LoadData.firstLoadData(this@MainActivity, rAdapter, rModels, null)

        /*val uri: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.epoxy_learn)
        videoView.setVideoURI(uri)
        videoView.seekTo(1)

        videoView.setOnClickListener{


            if (videoView.isPlaying){
                videoView.stopPlayback()
                imgPlayVideo.visibility = View.VISIBLE
            }else{
                videoView.start()
                videoView.resume();
                imgPlayVideo.visibility = View.GONE
            }
        }



        videoView.setOnCompletionListener {
            videoView.stopPlayback()
            videoView.seekTo(1)
            imgPlayVideo.visibility = View.VISIBLE
        }*/

        radioButtonDayere.setOnClickListener{
            imgMokaab.visibility = View.VISIBLE
            imgOstovaneh.visibility = View.GONE
            txToolB.visibility = View.VISIBLE
            etToolB.visibility = View.VISIBLE
            txArzA.setText("عرض a")
        }

        radioButtonMoraba.setOnClickListener{

            imgOstovaneh.visibility = View.VISIBLE
            imgMokaab.visibility = View.GONE
            txToolB.visibility = View.INVISIBLE
            etToolB.visibility = View.INVISIBLE
            txArzA.setText("d قطر")

        }





        var list = ArrayList<String>()
        list.add("")
        list.add("گرم")
        list.add("کیلوگرم")
        list.add("جرم")
        list.add("اونس")

        var spinnerArrayAdapter = ArrayAdapter<String>(
            this, R.layout.spinnter_dropdown_custom, list
        )
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinnter_dropdown_custom)
        spinnerMoredNiyazDar.adapter = spinnerArrayAdapter

        spinnerMoredNiyazDar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>, view: View, i:Int, l:Long
            ) {

                var selectedItem = adapterView.getItemAtPosition(i).toString()
                if (selectedItem.length <= 0) {

                } else {

                    txMoredNiyazDar.setText(selectedItem)


                    var darsadKol = etDarsadKol.text.toString().toDouble()
                    var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                    var meghdarDarsad=((darsadKol+darsadTaghsim)/darsadTaghsim)

                    if(txMoredNiyazDar.text.toString().equals("گرم")){

                        var totalHarder = geram/meghdarDarsad
                        txHarder.setText(String.format("%.3f", totalHarder) + " "+txMoredNiyazDar.text.toString())

                        var totalResin = geram - totalHarder
                        txResin.setText(String.format("%.3f", totalResin) + " "+txMoredNiyazDar.text.toString())

                    }else if(txMoredNiyazDar.text.toString().equals("کیلوگرم")){

                        var totalHarder = kilograms/meghdarDarsad
                        txHarder.setText(String.format("%.3f", totalHarder) + " "+txMoredNiyazDar.text.toString())

                        var totalResin = kilograms - totalHarder
                        txResin.setText(String.format("%.3f", totalResin) + " "+txMoredNiyazDar.text.toString())

                    }else if(txMoredNiyazDar.text.toString().equals("جرم")){

                        var totalHarder = pound/meghdarDarsad
                        txHarder.setText(String.format("%.3f", totalHarder) + " "+txMoredNiyazDar.text.toString())

                        var totalResin = pound - totalHarder
                        txResin.setText(String.format("%.3f", totalResin) + " "+txMoredNiyazDar.text.toString())

                    }else if(txMoredNiyazDar.text.toString().equals("اونس")){


                        var totalHarder = ounce/meghdarDarsad
                        txHarder.setText(String.format("%.3f", totalHarder) + " "+txMoredNiyazDar.text.toString())

                        var totalResin = ounce - totalHarder
                        txResin.setText(String.format("%.3f", totalResin) + " "+txMoredNiyazDar.text.toString())
                    }

                    spinnerMoredNiyazDar.setSelection(0)
                }

            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }



        var list2 = ArrayList<String>()
        list2.add("")
        list2.add("مکعب")
        list2.add("استوانه")

        var spinnerArrayAdapter2 = ArrayAdapter<String>(
            this, R.layout.spinnter_dropdown_custom, list2
        )
        spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinnter_dropdown_custom)
        spinnerMokaabYaoStovaneh.adapter = spinnerArrayAdapter2

        spinnerMokaabYaoStovaneh.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>, view: View, i:Int, l:Long
            ) {

                var selectedItem = adapterView.getItemAtPosition(i).toString()
                if (selectedItem.length <= 0) {

                } else {

                    if (selectedItem.equals("مکعب")){
                        imgMokaab.visibility = View.VISIBLE
                        imgOstovaneh.visibility = View.GONE
                        txToolB.visibility = View.VISIBLE
                        etToolB.visibility = View.VISIBLE
                        txArzA.setText("عرض a")


                    }else{
                        imgOstovaneh.visibility = View.VISIBLE
                        imgMokaab.visibility = View.GONE
                        txToolB.visibility = View.INVISIBLE
                        etToolB.visibility = View.INVISIBLE
                        txArzA.setText("قطر d")

                    }
                    //etCityName.hint = "نام شهر"
                    //txMoredNiyazDar.setText(selectedItem)
                    spinnerMokaabYaoStovaneh.setSelection(0)
                }

            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        radioButtonGeram.setOnClickListener{
            if (radioButtonCm.isChecked) {
                var ertefa = etErtefa.text.toString().toDouble()

                var toolB = etToolB.text.toString().toDouble()
                var arzA = etGhotrAndArzA.text.toString().toDouble()

                var hajmKol = (ertefa * toolB * arzA).toString().toDouble()
                var tarakomEpoxy = etTarakomEpoxy.text.toString().toDouble()

                geram = hajmKol * tarakomEpoxy
                var liter = (hajmKol / 1000).toDouble()
                txLitaaar.setText(String.format("%.3f", liter))

                kilograms = geram / 1000
                textView11.setText(String.format("%.3f", kilograms))

                pound = kilograms * 2.20
                txGermOrPound.setText(String.format("%.2f", pound))


                ounce = kilograms * 35.274
                txOunce.setText(String.format("%.2f", ounce))

                val twoDForm = DecimalFormat("#.##")
                val roundUpLitr = liter.toDouble()
                txLiter.setText(twoDForm.format(roundUpLitr).toString())

                var darsadKol = etDarsadKol.text.toString().toDouble()
                var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                var meghdarDarsad = ((darsadKol + darsadTaghsim) / darsadTaghsim)


                var totalHarder = kilograms / meghdarDarsad
                txHarder.setText(
                    String.format(
                        "%.3f",
                        totalHarder*1000
                    ) + " " + txMoredNiyazDar.text.toString()
                )

                var totalResin = kilograms - totalHarder
                txResin.setText(
                    String.format(
                        "%.3f",
                        totalResin*1000
                    ) + " " + txMoredNiyazDar.text.toString()
                )



            }else{

                var ertefa = etErtefa.text.toString().toDouble()

                var toolB = etToolB.text.toString().toDouble()
                var arzA = etGhotrAndArzA.text.toString().toDouble()

                var hajmKol = (ertefa * toolB * arzA).toString().toDouble()
                var tarakomEpoxy = etTarakomEpoxy.text.toString().toDouble()

                //val formatter: NumberFormat = DecimalFormat("#.##")
                geram = hajmKol * tarakomEpoxy
                var liter = (hajmKol / 1000).toDouble()
                txLitaaar.setText(String.format("%.3f", liter/1000))
                //txLitaaar.setText(liter.toString())


                kilograms = geram / 1000
                textView11.setText(String.format("%.3f", kilograms/1000))

                pound = kilograms * 2.20
                txGermOrPound.setText(String.format("%.2f", pound))


                ounce = kilograms * 35.274
                txOunce.setText(String.format("%.2f", ounce))


                //var hajmKolDb =hajmKol.toDouble()
                //var liter = (hajmKol/1000).toDouble()

                val twoDForm = DecimalFormat("#.##")
                val roundUpLitr = liter.toDouble()
                txLiter.setText(twoDForm.format(roundUpLitr).toString())

                var darsadKol = etDarsadKol.text.toString().toDouble()
                var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                var meghdarDarsad = ((darsadKol + darsadTaghsim) / darsadTaghsim)


                var totalHarder = kilograms / meghdarDarsad
                txHarder.setText(
                    String.format(
                        "%.3f",
                        totalHarder
                    ) + " " + txMoredNiyazDar.text.toString()
                )

                var totalResin = kilograms - totalHarder
                txResin.setText(
                    String.format(
                        "%.3f",
                        totalResin
                    ) + " " + txMoredNiyazDar.text.toString()
                )
            }

        }


        radioButtonKg.setOnClickListener{
            if (radioButtonCm.isChecked) {

                var ertefa = etErtefa.text.toString().toDouble()

                var toolB = etToolB.text.toString().toDouble()
                var arzA = etGhotrAndArzA.text.toString().toDouble()

                var hajmKol = (ertefa * toolB * arzA).toString().toDouble()
                var tarakomEpoxy = etTarakomEpoxy.text.toString().toDouble()

                //val formatter: NumberFormat = DecimalFormat("#.##")
                geram = hajmKol * tarakomEpoxy
                var liter = (hajmKol / 1000).toDouble()
                txLitaaar.setText(String.format("%.3f", liter))
                //txLitaaar.setText(liter.toString())


                kilograms = geram / 1000
                textView11.setText(String.format("%.3f", kilograms))

                pound = kilograms * 2.20
                txGermOrPound.setText(String.format("%.2f", pound))


                ounce = kilograms * 35.274
                txOunce.setText(String.format("%.2f", ounce))


                //var hajmKolDb =hajmKol.toDouble()
                //var liter = (hajmKol/1000).toDouble()

                val twoDForm = DecimalFormat("#.##")
                val roundUpLitr = liter.toDouble()
                txLiter.setText(twoDForm.format(roundUpLitr).toString())

                var darsadKol = etDarsadKol.text.toString().toDouble()
                var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                var meghdarDarsad = ((darsadKol + darsadTaghsim) / darsadTaghsim)


                var totalHarder = kilograms / meghdarDarsad
                txHarder.setText(
                    String.format(
                        "%.3f",
                        totalHarder
                    ) + " " + "کیلوگرم"
                )

                var totalResin = kilograms - totalHarder
                txResin.setText(
                    String.format(
                        "%.3f",
                        totalResin
                    ) + " " + "کیلوگرم"
                )
            }else{

                var ertefa = etErtefa.text.toString().toDouble()

                var toolB = etToolB.text.toString().toDouble()
                var arzA = etGhotrAndArzA.text.toString().toDouble()

                var hajmKol = (ertefa * toolB * arzA).toString().toDouble()
                var tarakomEpoxy = etTarakomEpoxy.text.toString().toDouble()

                //val formatter: NumberFormat = DecimalFormat("#.##")
                geram = hajmKol * tarakomEpoxy
                var liter = (hajmKol / 1000).toDouble()
                txLitaaar.setText(String.format("%.3f", liter/1000))
                //txLitaaar.setText(liter.toString())


                kilograms = geram / 1000
                textView11.setText(String.format("%.3f", kilograms/1000))

                pound = kilograms * 2.20
                txGermOrPound.setText(String.format("%.2f", pound))


                ounce = kilograms * 35.274
                txOunce.setText(String.format("%.2f", ounce))


                //var hajmKolDb =hajmKol.toDouble()
                //var liter = (hajmKol/1000).toDouble()

                val twoDForm = DecimalFormat("#.##")
                val roundUpLitr = liter.toDouble()
                txLiter.setText(twoDForm.format(roundUpLitr).toString())

                var darsadKol = etDarsadKol.text.toString().toDouble()
                var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                var meghdarDarsad = ((darsadKol + darsadTaghsim) / darsadTaghsim)


                var totalHarder = kilograms / meghdarDarsad
                txHarder.setText(
                    String.format(
                        "%.3f",
                        totalHarder/1000
                    ) + " " + "کیلوگرم"
                )

                var totalResin = kilograms - totalHarder
                txResin.setText(
                    String.format(
                        "%.3f",
                        totalResin/1000
                    ) + " " + "کیلوگرم"
                )

            }
        }

        calculate.setOnClickListener{

            if(imgMokaab.visibility == View.VISIBLE) {
                if (etErtefa.text.isEmpty() || etToolB.text.isEmpty() || etGhotrAndArzA.text.isEmpty()) {
                    Toast.makeText(this, "لطفا همه فیلد ها را پر کنید!", Toast.LENGTH_SHORT).show()
                } else {

                    if (radioButtonCm.isChecked) {


                        if (radioButtonGeram.isChecked){

                            var ertefa = etErtefa.text.toString().toDouble()

                            var toolB = etToolB.text.toString().toDouble()
                            var arzA = etGhotrAndArzA.text.toString().toDouble()

                            var hajmKol = (ertefa * toolB * arzA).toString().toDouble()
                            var tarakomEpoxy = etTarakomEpoxy.text.toString().toDouble()

                            //val formatter: NumberFormat = DecimalFormat("#.##")
                            geram = hajmKol * tarakomEpoxy
                            var liter = (hajmKol / 1000).toDouble()
                            txLitaaar.setText(String.format("%.3f", liter))
                            //txLitaaar.setText(liter.toString())


                            kilograms = geram / 1000
                            textView11.setText(String.format("%.3f", kilograms))

                            pound = kilograms * 2.20
                            txGermOrPound.setText(String.format("%.2f", pound))


                            ounce = kilograms * 35.274
                            txOunce.setText(String.format("%.2f", ounce))


                            //var hajmKolDb =hajmKol.toDouble()
                            //var liter = (hajmKol/1000).toDouble()

                            val twoDForm = DecimalFormat("#.##")
                            val roundUpLitr = liter.toDouble()
                            txLiter.setText(twoDForm.format(roundUpLitr).toString())

                            var darsadKol = etDarsadKol.text.toString().toDouble()
                            var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                            var meghdarDarsad = ((darsadKol + darsadTaghsim) / darsadTaghsim)


                            var totalHarder = kilograms / meghdarDarsad
                            txHarder.setText(
                                String.format(
                                    "%.3f",
                                    totalHarder*1000
                                ) + " " + txMoredNiyazDar.text.toString()
                            )

                            var totalResin = kilograms - totalHarder
                            txResin.setText(
                                String.format(
                                    "%.3f",
                                    totalResin*1000
                                ) + " " + txMoredNiyazDar.text.toString()
                            )


                        }else{

                        var ertefa = etErtefa.text.toString().toDouble()

                        var toolB = etToolB.text.toString().toDouble()
                        var arzA = etGhotrAndArzA.text.toString().toDouble()

                        var hajmKol = (ertefa * toolB * arzA).toString().toDouble()
                        var tarakomEpoxy = etTarakomEpoxy.text.toString().toDouble()

                        //val formatter: NumberFormat = DecimalFormat("#.##")
                        geram = hajmKol * tarakomEpoxy
                        var liter = (hajmKol / 1000).toDouble()
                        txLitaaar.setText(String.format("%.3f", liter))
                        //txLitaaar.setText(liter.toString())


                        kilograms = geram / 1000
                        textView11.setText(String.format("%.3f", kilograms))

                        pound = kilograms * 2.20
                        txGermOrPound.setText(String.format("%.2f", pound))


                        ounce = kilograms * 35.274
                        txOunce.setText(String.format("%.2f", ounce))


                        //var hajmKolDb =hajmKol.toDouble()
                        //var liter = (hajmKol/1000).toDouble()

                        val twoDForm = DecimalFormat("#.##")
                        val roundUpLitr = liter.toDouble()
                        txLiter.setText(twoDForm.format(roundUpLitr).toString())

                        var darsadKol = etDarsadKol.text.toString().toDouble()
                        var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                        var meghdarDarsad = ((darsadKol + darsadTaghsim) / darsadTaghsim)


                        var totalHarder = kilograms / meghdarDarsad
                        txHarder.setText(
                            String.format(
                                "%.3f",
                                totalHarder
                            ) + " " + "کیلوگرم"
                        )

                        var totalResin = kilograms - totalHarder
                        txResin.setText(
                            String.format(
                                "%.3f",
                                totalResin
                            ) + " " + "کیلوگرم"
                        )

                        }
                }else{

                        if (radioButtonGeram.isChecked){
                            var ertefa = etErtefa.text.toString().toDouble()

                            var toolB = etToolB.text.toString().toDouble()
                            var arzA = etGhotrAndArzA.text.toString().toDouble()

                            var hajmKol = (ertefa * toolB * arzA).toString().toDouble()
                            var tarakomEpoxy = etTarakomEpoxy.text.toString().toDouble()

                            //val formatter: NumberFormat = DecimalFormat("#.##")
                            geram = hajmKol * tarakomEpoxy
                            var liter = (hajmKol / 1000).toDouble()
                            txLitaaar.setText(String.format("%.3f", liter/1000))
                            //txLitaaar.setText(liter.toString())


                            kilograms = geram / 1000
                            textView11.setText(String.format("%.3f", kilograms/1000))

                            pound = kilograms * 2.20
                            txGermOrPound.setText(String.format("%.2f", pound))


                            ounce = kilograms * 35.274
                            txOunce.setText(String.format("%.2f", ounce))


                            //var hajmKolDb =hajmKol.toDouble()
                            //var liter = (hajmKol/1000).toDouble()

                            val twoDForm = DecimalFormat("#.##")
                            val roundUpLitr = liter.toDouble()
                            txLiter.setText(twoDForm.format(roundUpLitr).toString())

                            var darsadKol = etDarsadKol.text.toString().toDouble()
                            var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                            var meghdarDarsad = ((darsadKol + darsadTaghsim) / darsadTaghsim)


                            var totalHarder = kilograms / meghdarDarsad
                            txHarder.setText(
                                String.format(
                                    "%.3f",
                                    totalHarder
                                ) + " " + txMoredNiyazDar.text.toString()
                            )

                            var totalResin = kilograms - totalHarder
                            txResin.setText(
                                String.format(
                                    "%.3f",
                                    totalResin
                                ) + " " + txMoredNiyazDar.text.toString()
                            )
                        }else{

                        var ertefa = etErtefa.text.toString().toDouble()

                        var toolB = etToolB.text.toString().toDouble()
                        var arzA = etGhotrAndArzA.text.toString().toDouble()

                        var hajmKol = (ertefa * toolB * arzA).toString().toDouble()
                        var tarakomEpoxy = etTarakomEpoxy.text.toString().toDouble()

                        //val formatter: NumberFormat = DecimalFormat("#.##")
                        geram = hajmKol * tarakomEpoxy
                        var liter = (hajmKol / 1000).toDouble()
                        txLitaaar.setText(String.format("%.3f", liter/1000))
                        //txLitaaar.setText(liter.toString())


                        kilograms = geram / 1000
                        textView11.setText(String.format("%.3f", kilograms/1000))

                        pound = kilograms * 2.20
                        txGermOrPound.setText(String.format("%.2f", pound))


                        ounce = kilograms * 35.274
                        txOunce.setText(String.format("%.2f", ounce))


                        //var hajmKolDb =hajmKol.toDouble()
                        //var liter = (hajmKol/1000).toDouble()

                        val twoDForm = DecimalFormat("#.##")
                        val roundUpLitr = liter.toDouble()
                        txLiter.setText(twoDForm.format(roundUpLitr).toString())

                        var darsadKol = etDarsadKol.text.toString().toDouble()
                        var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                        var meghdarDarsad = ((darsadKol + darsadTaghsim) / darsadTaghsim)


                        var totalHarder = kilograms / meghdarDarsad
                        txHarder.setText(
                            String.format(
                                "%.3f",
                                totalHarder/1000
                            ) + " " + "کیلوگرم"
                        )

                        var totalResin = kilograms - totalHarder
                        txResin.setText(
                            String.format(
                                "%.3f",
                                totalResin/1000
                            ) + " " + "کیلوگرم"
                        )

                        }
                    }
                }


            }else{
                if(etErtefa.text.isEmpty() || etGhotrAndArzA.text.isEmpty()){
                    Toast.makeText(this,"لطفا همه فیلد ها را پر کنید!",Toast.LENGTH_SHORT).show()

                }else{

                    if (radioButtonCm.isChecked){
                        //Centemeter
                        //Toast.makeText(this,"cm",Toast.LENGTH_LONG).show()

                        if (radioButtonGeram.isChecked){

                            var hajmKol = 0.0

                            var ertefa =etErtefa.text.toString().toDouble()
                            var gotrD =etGhotrAndArzA.text.toString().toDouble()

                            var tarakomEpoxy = etTarakomEpoxy.text.toString().toDouble()

                            var shoaa = gotrD/2
                            shoaa = shoaa * shoaa

                            var masahat = shoaa * Math.PI

                            var result = (masahat/100)*(ertefa*10)
                            result = result/100
                            txLitaaar.setText(String.format("%.3f", result))

                            geram = hajmKol * tarakomEpoxy

                            txGeram.setText(geram.toString())
                            var hajmKolAsli = result*tarakomEpoxy

                            //kilograms = geram / 1000

                            textView11.setText(String.format("%.3f", hajmKolAsli))

                            //textView11.setText((hajmKol*tarakomEpoxy).toString())


                            pound = kilograms*2.20
                            txGermOrPound.setText(String.format("%.2f", pound))


                            ounce = kilograms*35.274
                            txOunce.setText(String.format("%.2f", ounce))


                            //var hajmKolDb =hajmKol.toDouble()
                            var liter = (hajmKol/1000).toDouble()

                            val twoDForm = DecimalFormat("#.##")
                            val roundUpLitr = liter.toDouble()
                            txLiter.setText(twoDForm.format(roundUpLitr).toString())

                            var darsadKol = etDarsadKol.text.toString().toDouble()
                            var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                            var meghdarDarsad=((darsadKol+darsadTaghsim)/darsadTaghsim)

                            var totalHarder = hajmKolAsli/meghdarDarsad
                            txHarder.setText(String.format("%.3f", totalHarder*1000) + " "+txMoredNiyazDar.text.toString())

                            var totalResin = hajmKolAsli - totalHarder
                            txResin.setText(String.format("%.3f", totalResin*1000) + " "+txMoredNiyazDar.text.toString())


                        }else{



                        var hajmKol = 0.0

                        var ertefa =etErtefa.text.toString().toDouble()
                        var gotrD =etGhotrAndArzA.text.toString().toDouble()

                        var tarakomEpoxy = etTarakomEpoxy.text.toString().toDouble()

                        var shoaa = gotrD/2
                        shoaa = shoaa * shoaa

                        var masahat = shoaa * Math.PI

                        var result = (masahat/100)*(ertefa*10)
                        result = result/100
                        txLitaaar.setText(String.format("%.3f", result))

                        geram = hajmKol * tarakomEpoxy

                        txGeram.setText(geram.toString())
                        var hajmKolAsli = result*tarakomEpoxy

                        //kilograms = geram / 1000

                        textView11.setText(String.format("%.3f", hajmKolAsli))

                        //textView11.setText((hajmKol*tarakomEpoxy).toString())


                        pound = kilograms*2.20
                        txGermOrPound.setText(String.format("%.2f", pound))


                        ounce = kilograms*35.274
                        txOunce.setText(String.format("%.2f", ounce))


                        //var hajmKolDb =hajmKol.toDouble()
                        var liter = (hajmKol/1000).toDouble()

                        val twoDForm = DecimalFormat("#.##")
                        val roundUpLitr = liter.toDouble()
                        txLiter.setText(twoDForm.format(roundUpLitr).toString())

                        var darsadKol = etDarsadKol.text.toString().toDouble()
                        var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                        var meghdarDarsad=((darsadKol+darsadTaghsim)/darsadTaghsim)

                        var totalHarder = hajmKolAsli/meghdarDarsad
                        txHarder.setText(String.format("%.3f", totalHarder) + " "+"کیلوگرم")

                        var totalResin = hajmKolAsli - totalHarder
                        txResin.setText(String.format("%.3f", totalResin) + " "+"کیلوگرم")

                        /*  if(txMoredNiyazDar.text.toString().equals("گرم")){

                              var totalHarder = geram/meghdarDarsad
                              txHarder.setText(String.format("%.2f", totalHarder) + " "+txMoredNiyazDar.text.toString())

                              var totalResin = geram - totalHarder
                              txResin.setText(String.format("%.2f", totalResin) + " "+txMoredNiyazDar.text.toString())

                          }*/





                        }
                    }else{
                        //milemeter


                        if (radioButtonGeram.isChecked){
                            var hajmKol = 0.0

                            var ertefa =etErtefa.text.toString().toDouble()
                            var gotrD =etGhotrAndArzA.text.toString().toDouble()
                            var tarakomEpoxy = etTarakomEpoxy.text.toString().toDouble()

                            var shoaa = gotrD/2
                            shoaa = shoaa * shoaa

                            var masahat = shoaa * Math.PI

                            var result = (masahat/100)*(ertefa*10)
                            result = result/100
                            txLitaaar.setText(String.format("%.3f", result/1000))

                            geram = (hajmKol * tarakomEpoxy)/1000

                            txGeram.setText(geram.toString())
                            var hajmKolAsli = result*tarakomEpoxy

                            textView11.setText(String.format("%.3f", hajmKolAsli/1000))


                            pound = kilograms*2.20
                            txGermOrPound.setText(String.format("%.2f", pound))


                            ounce = kilograms*35.274
                            txOunce.setText(String.format("%.2f", ounce))


                            var liter = (hajmKol/1000).toDouble()

                            val twoDForm = DecimalFormat("#.##")
                            val roundUpLitr = liter.toDouble()
                            txLiter.setText(twoDForm.format(roundUpLitr).toString())

                            var darsadKol = etDarsadKol.text.toString().toDouble()
                            var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                            var meghdarDarsad=((darsadKol+darsadTaghsim)/darsadTaghsim)

                            var totalHarder = hajmKolAsli/meghdarDarsad
                            txHarder.setText(String.format("%.3f", totalHarder) + " "+txMoredNiyazDar.text.toString())

                            var totalResin = hajmKolAsli - totalHarder
                            txResin.setText(String.format("%.3f", totalResin) + " "+txMoredNiyazDar.text.toString())


                        }else{
                        var hajmKol = 0.0

                        var ertefa =etErtefa.text.toString().toDouble()
                        var gotrD =etGhotrAndArzA.text.toString().toDouble()
                        var tarakomEpoxy = etTarakomEpoxy.text.toString().toDouble()

                        var shoaa = gotrD/2
                        shoaa = shoaa * shoaa

                        var masahat = shoaa * Math.PI

                        var result = (masahat/100)*(ertefa*10)
                        result = result/100
                        txLitaaar.setText(String.format("%.3f", result/1000))

                        geram = (hajmKol * tarakomEpoxy)/1000

                        txGeram.setText(geram.toString())
                        var hajmKolAsli = result*tarakomEpoxy

                        textView11.setText(String.format("%.3f", hajmKolAsli/1000))


                        pound = kilograms*2.20
                        txGermOrPound.setText(String.format("%.2f", pound))


                        ounce = kilograms*35.274
                        txOunce.setText(String.format("%.2f", ounce))


                        var liter = (hajmKol/1000).toDouble()

                        val twoDForm = DecimalFormat("#.##")
                        val roundUpLitr = liter.toDouble()
                        txLiter.setText(twoDForm.format(roundUpLitr).toString())

                        var darsadKol = etDarsadKol.text.toString().toDouble()
                        var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                        var meghdarDarsad=((darsadKol+darsadTaghsim)/darsadTaghsim)

                        var totalHarder = hajmKolAsli/meghdarDarsad
                        txHarder.setText(String.format("%.3f", totalHarder/1000) + " "+"کیلوگرم")

                        var totalResin = hajmKolAsli - totalHarder
                        txResin.setText(String.format("%.3f", totalResin/1000) + " "+"کیلوگرم")
                        }

                    }




                }

            }



        }

        var listReset = ArrayList<String>()
        listReset.add("")
        listReset.add("تنظیم مجدد")

        var spinnerArrayAdapter3 = ArrayAdapter<String>(
            this, R.layout.spinnter_dropdown_custom, listReset
        )
        spinnerArrayAdapter3.setDropDownViewResource(R.layout.spinnter_dropdown_custom)
        spinnerReset.adapter = spinnerArrayAdapter3

        spinnerReset.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>, view: View, i:Int, l:Long
            ) {

                var selectedItem = adapterView.getItemAtPosition(i).toString()
                if (selectedItem.length <= 0) {

                } else {
                    etErtefa.setText("")
                    etToolB.setText("")
                    etGhotrAndArzA.setText("")
                    etTarakomEpoxy.setText("1.13")
                    etDarsadTaghsim.setText("50")
                    etDarsadKol.setText("100")
                    textView11.setText("0")
                    txLitaaar.setText("0")
                    txHarder.setText("")
                    txResin.setText("")
                    spinnerReset.setSelection(0)
                }

            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }




    }

    fun setSplashy() {
        Splashy(this)         // For JAVA : new Splashy(this)
            .setLogo(R.drawable.splashy_2)
            .setLogoWHinDp(300, 300)
            .setTitle("ماشین حساب رزین اپوکسی")
            .setTitleColor(R.color.epoxyTextColor).setTitleSize(20F)
            .showProgress(true)
            .setProgressColor(R.color.colorAccent).setFullScreen(true).setTime(3000).show()
    }
}
