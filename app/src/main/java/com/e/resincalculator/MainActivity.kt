package com.e.resincalculator

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.javiersantos.appupdater.AppUpdater
import com.github.javiersantos.appupdater.enums.UpdateFrom
import com.rbddevs.splashy.Splashy
import kotlinx.android.synthetic.main.activity_main.*
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
        setContentView(R.layout.activity_main)

        setSplashy()
    s sad d
        //line zir baraye update automatic ee.
        val appUpdater = AppUpdater(this).setUpdateFrom(UpdateFrom.JSON)
            .setUpdateJSON("http://robika.ir/ultitled/practice/resin_epoxy_update_checker.json")
            .setTitleOnUpdateAvailable("بروزرسانی جدید موجوده!").setButtonUpdate("بروزرسانی")
            .setButtonDismiss("فعلا نه").setButtonDoNotShowAgain("")
        appUpdater.start()


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
            txArzA.setText("عرض")
        }

        radioButtonMoraba.setOnClickListener{

            imgOstovaneh.visibility = View.VISIBLE
            imgMokaab.visibility = View.GONE
            txToolB.visibility = View.INVISIBLE
            etToolB.visibility = View.INVISIBLE
            txArzA.setText("قطر")

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
                        txHarder.setText(String.format("%.2f", totalHarder) + " "+txMoredNiyazDar.text.toString())

                        var totalResin = geram - totalHarder
                        txResin.setText(String.format("%.2f", totalResin) + " "+txMoredNiyazDar.text.toString())

                    }else if(txMoredNiyazDar.text.toString().equals("کیلوگرم")){

                        var totalHarder = kilograms/meghdarDarsad
                        txHarder.setText(String.format("%.2f", totalHarder) + " "+txMoredNiyazDar.text.toString())

                        var totalResin = kilograms - totalHarder
                        txResin.setText(String.format("%.2f", totalResin) + " "+txMoredNiyazDar.text.toString())

                    }else if(txMoredNiyazDar.text.toString().equals("جرم")){

                        var totalHarder = pound/meghdarDarsad
                        txHarder.setText(String.format("%.2f", totalHarder) + " "+txMoredNiyazDar.text.toString())

                        var totalResin = pound - totalHarder
                        txResin.setText(String.format("%.2f", totalResin) + " "+txMoredNiyazDar.text.toString())

                    }else if(txMoredNiyazDar.text.toString().equals("اونس")){


                        var totalHarder = ounce/meghdarDarsad
                        txHarder.setText(String.format("%.2f", totalHarder) + " "+txMoredNiyazDar.text.toString())

                        var totalResin = ounce - totalHarder
                        txResin.setText(String.format("%.2f", totalResin) + " "+txMoredNiyazDar.text.toString())
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
                        txArzA.setText("عرض آ")


                    }else{
                        imgOstovaneh.visibility = View.VISIBLE
                        imgMokaab.visibility = View.GONE
                        txToolB.visibility = View.INVISIBLE
                        etToolB.visibility = View.INVISIBLE
                        txArzA.setText("قطر د")

                    }
                    //etCityName.hint = "نام شهر"
                    //txMoredNiyazDar.setText(selectedItem)
                    spinnerMokaabYaoStovaneh.setSelection(0)
                }

            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
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
                            txLitaaar.setText(String.format("%.2f", liter))
                            //txLitaaar.setText(liter.toString())


                            kilograms = geram / 1000
                            textView11.setText(String.format("%.2f", kilograms))

                            pound = kilograms * 2.20
                            txGermOrPound.setText(String.format("%.2f", pound))


                            ounce = kilograms * 35.274
                            txOunce.setText(String.format("%.2f", ounce))


                            //var hajmKolDb =hajmKol.toDouble()
                            //var liter = (hajmKol/1000).toDouble()

                            val twoDForm = DecimalFormat("#.##")
                            val roundUpLitr = String.format("%.3f", liter).toDouble()
                            txLiter.setText(twoDForm.format(roundUpLitr).toString())

                            var darsadKol = etDarsadKol.text.toString().toDouble()
                            var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                            var meghdarDarsad = ((darsadKol + darsadTaghsim) / darsadTaghsim)


                            var totalHarder = kilograms / meghdarDarsad
                            txHarder.setText(
                                String.format(
                                    "%.2f",
                                    totalHarder*1000
                                ) + " " + txMoredNiyazDar.text.toString()
                            )

                            var totalResin = kilograms - totalHarder
                            txResin.setText(
                                String.format(
                                    "%.2f",
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
                        txLitaaar.setText(String.format("%.2f", liter))
                        //txLitaaar.setText(liter.toString())


                        kilograms = geram / 1000
                        textView11.setText(String.format("%.2f", kilograms))

                        pound = kilograms * 2.20
                        txGermOrPound.setText(String.format("%.2f", pound))


                        ounce = kilograms * 35.274
                        txOunce.setText(String.format("%.2f", ounce))


                        //var hajmKolDb =hajmKol.toDouble()
                        //var liter = (hajmKol/1000).toDouble()

                        val twoDForm = DecimalFormat("#.##")
                        val roundUpLitr = String.format("%.3f", liter).toDouble()
                        txLiter.setText(twoDForm.format(roundUpLitr).toString())

                        var darsadKol = etDarsadKol.text.toString().toDouble()
                        var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                        var meghdarDarsad = ((darsadKol + darsadTaghsim) / darsadTaghsim)


                        var totalHarder = kilograms / meghdarDarsad
                        txHarder.setText(
                            String.format(
                                "%.2f",
                                totalHarder
                            ) + " " + "کیلوگرم"
                        )

                        var totalResin = kilograms - totalHarder
                        txResin.setText(
                            String.format(
                                "%.2f",
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
                            txLitaaar.setText(String.format("%.2f", liter/1000))
                            //txLitaaar.setText(liter.toString())


                            kilograms = geram / 1000
                            textView11.setText(String.format("%.2f", kilograms/1000))

                            pound = kilograms * 2.20
                            txGermOrPound.setText(String.format("%.2f", pound))


                            ounce = kilograms * 35.274
                            txOunce.setText(String.format("%.2f", ounce))


                            //var hajmKolDb =hajmKol.toDouble()
                            //var liter = (hajmKol/1000).toDouble()

                            val twoDForm = DecimalFormat("#.##")
                            val roundUpLitr = String.format("%.3f", liter).toDouble()
                            txLiter.setText(twoDForm.format(roundUpLitr).toString())

                            var darsadKol = etDarsadKol.text.toString().toDouble()
                            var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                            var meghdarDarsad = ((darsadKol + darsadTaghsim) / darsadTaghsim)


                            var totalHarder = kilograms / meghdarDarsad
                            txHarder.setText(
                                String.format(
                                    "%.2f",
                                    totalHarder
                                ) + " " + txMoredNiyazDar.text.toString()
                            )

                            var totalResin = kilograms - totalHarder
                            txResin.setText(
                                String.format(
                                    "%.2f",
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
                        txLitaaar.setText(String.format("%.2f", liter/1000))
                        //txLitaaar.setText(liter.toString())


                        kilograms = geram / 1000
                        textView11.setText(String.format("%.2f", kilograms/1000))

                        pound = kilograms * 2.20
                        txGermOrPound.setText(String.format("%.2f", pound))


                        ounce = kilograms * 35.274
                        txOunce.setText(String.format("%.2f", ounce))


                        //var hajmKolDb =hajmKol.toDouble()
                        //var liter = (hajmKol/1000).toDouble()

                        val twoDForm = DecimalFormat("#.##")
                        val roundUpLitr = String.format("%.3f", liter).toDouble()
                        txLiter.setText(twoDForm.format(roundUpLitr).toString())

                        var darsadKol = etDarsadKol.text.toString().toDouble()
                        var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                        var meghdarDarsad = ((darsadKol + darsadTaghsim) / darsadTaghsim)


                        var totalHarder = kilograms / meghdarDarsad
                        txHarder.setText(
                            String.format(
                                "%.2f",
                                totalHarder/1000
                            ) + " " + "کیلوگرم"
                        )

                        var totalResin = kilograms - totalHarder
                        txResin.setText(
                            String.format(
                                "%.2f",
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
                            txLitaaar.setText(String.format("%.2f", result))

                            geram = hajmKol * tarakomEpoxy

                            txGeram.setText(geram.toString())
                            var hajmKolAsli = result*tarakomEpoxy

                            //kilograms = geram / 1000

                            textView11.setText(String.format("%.2f", hajmKolAsli))

                            //textView11.setText((hajmKol*tarakomEpoxy).toString())


                            pound = kilograms*2.20
                            txGermOrPound.setText(String.format("%.2f", pound))


                            ounce = kilograms*35.274
                            txOunce.setText(String.format("%.2f", ounce))


                            //var hajmKolDb =hajmKol.toDouble()
                            var liter = (hajmKol/1000).toDouble()

                            val twoDForm = DecimalFormat("#.##")
                            val roundUpLitr = String.format("%.3f", liter).toDouble()
                            txLiter.setText(twoDForm.format(roundUpLitr).toString())

                            var darsadKol = etDarsadKol.text.toString().toDouble()
                            var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                            var meghdarDarsad=((darsadKol+darsadTaghsim)/darsadTaghsim)

                            var totalHarder = hajmKolAsli/meghdarDarsad
                            txHarder.setText(String.format("%.2f", totalHarder*1000) + " "+txMoredNiyazDar.text.toString())

                            var totalResin = hajmKolAsli - totalHarder
                            txResin.setText(String.format("%.2f", totalResin*1000) + " "+txMoredNiyazDar.text.toString())


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
                        txLitaaar.setText(String.format("%.2f", result))

                        geram = hajmKol * tarakomEpoxy

                        txGeram.setText(geram.toString())
                        var hajmKolAsli = result*tarakomEpoxy

                        //kilograms = geram / 1000

                        textView11.setText(String.format("%.2f", hajmKolAsli))

                        //textView11.setText((hajmKol*tarakomEpoxy).toString())


                        pound = kilograms*2.20
                        txGermOrPound.setText(String.format("%.2f", pound))


                        ounce = kilograms*35.274
                        txOunce.setText(String.format("%.2f", ounce))


                        //var hajmKolDb =hajmKol.toDouble()
                        var liter = (hajmKol/1000).toDouble()

                        val twoDForm = DecimalFormat("#.##")
                        val roundUpLitr = String.format("%.3f", liter).toDouble()
                        txLiter.setText(twoDForm.format(roundUpLitr).toString())

                        var darsadKol = etDarsadKol.text.toString().toDouble()
                        var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                        var meghdarDarsad=((darsadKol+darsadTaghsim)/darsadTaghsim)

                        var totalHarder = hajmKolAsli/meghdarDarsad
                        txHarder.setText(String.format("%.2f", totalHarder) + " "+"کیلوگرم")

                        var totalResin = hajmKolAsli - totalHarder
                        txResin.setText(String.format("%.2f", totalResin) + " "+"کیلوگرم")

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
                            txLitaaar.setText(String.format("%.2f", result/1000))

                            geram = (hajmKol * tarakomEpoxy)/1000

                            txGeram.setText(geram.toString())
                            var hajmKolAsli = result*tarakomEpoxy

                            textView11.setText(String.format("%.2f", hajmKolAsli/1000))


                            pound = kilograms*2.20
                            txGermOrPound.setText(String.format("%.2f", pound))


                            ounce = kilograms*35.274
                            txOunce.setText(String.format("%.2f", ounce))


                            var liter = (hajmKol/1000).toDouble()

                            val twoDForm = DecimalFormat("#.##")
                            val roundUpLitr = String.format("%.3f", liter).toDouble()
                            txLiter.setText(twoDForm.format(roundUpLitr).toString())

                            var darsadKol = etDarsadKol.text.toString().toDouble()
                            var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                            var meghdarDarsad=((darsadKol+darsadTaghsim)/darsadTaghsim)

                            var totalHarder = hajmKolAsli/meghdarDarsad
                            txHarder.setText(String.format("%.2f", totalHarder) + " "+txMoredNiyazDar.text.toString())

                            var totalResin = hajmKolAsli - totalHarder
                            txResin.setText(String.format("%.2f", totalResin) + " "+txMoredNiyazDar.text.toString())


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
                        txLitaaar.setText(String.format("%.2f", result/1000))

                        geram = (hajmKol * tarakomEpoxy)/1000

                        txGeram.setText(geram.toString())
                        var hajmKolAsli = result*tarakomEpoxy

                        textView11.setText(String.format("%.2f", hajmKolAsli/1000))


                        pound = kilograms*2.20
                        txGermOrPound.setText(String.format("%.2f", pound))


                        ounce = kilograms*35.274
                        txOunce.setText(String.format("%.2f", ounce))


                        var liter = (hajmKol/1000).toDouble()

                        val twoDForm = DecimalFormat("#.##")
                        val roundUpLitr = String.format("%.3f", liter).toDouble()
                        txLiter.setText(twoDForm.format(roundUpLitr).toString())

                        var darsadKol = etDarsadKol.text.toString().toDouble()
                        var darsadTaghsim = etDarsadTaghsim.text.toString().toDouble()
                        var meghdarDarsad=((darsadKol+darsadTaghsim)/darsadTaghsim)

                        var totalHarder = hajmKolAsli/meghdarDarsad
                        txHarder.setText(String.format("%.2f", totalHarder/1000) + " "+"کیلوگرم")

                        var totalResin = hajmKolAsli - totalHarder
                        txResin.setText(String.format("%.2f", totalResin/1000) + " "+"کیلوگرم")
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
