package com.example.toni.myapplication;

        import android.app.Dialog;
        import android.app.ProgressDialog;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
    }
}

//public class QRScanner extends AppCompatActivity {
//
//    public ArrayList<HashMap<String, String>> Item_List;
//    ProgressDialog PD;
//
//
//    //Database API
//    //String url = "http://ezpill.filoteum.com/read_prescription.php";
//    String url = "http://ezpill.filoteum.com/read_report.php";
//
//    // JSON Node names
//    public static final String PRESCRIPTON_NO   = "pres_no";
//    public static final String PATIENT_PHONE    = "patient_phone";
//    public static final String DRUG_ID          = "drug_id";
//    public static final String DRUG_DAYS        = "drug_days";
//    public static final String DRUG_P_DAY       = "drug_p_day";
//    public static final String DATE_ISSUE       = "date_issue";
//    public static final String DATE_END         = "date_end";
//    //public static final String RESPONSE         = "prescription";
//
//    //DEBUG REPORT
//    public static final String DATE_TAKEN       ="date_taken";
//    public static final String RESPONSE         = "report";
//
//    TextView barcodeInfo;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_qrscanner);
//
//        //final TextView barcodeInfo = (TextView)findViewById(R.id.code_info);
//        barcodeInfo = (TextView)findViewById(R.id.code_info);
//        final Button scanButton = (Button)findViewById(R.id.scanButton);
//
//        Item_List = new ArrayList<HashMap<String, String>>();
//
//        PD = new ProgressDialog(this);
//        PD.setMessage("Loading.....");
//        PD.setCancelable(false);
//
//        scanButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //final Dialog dialog = new Dialog(view.getContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
//                final Dialog dialog = new Dialog(view.getContext());
//                dialog.setContentView(R.layout.dialog_scanner);
//                dialog.setTitle("Scanning Code");
//
//                // set the custom dialog components - text, image and button
//                final SurfaceView cameraView = (SurfaceView) dialog.findViewById(R.id.camera_view);
//                final Button cancelButton = (Button) dialog.findViewById(R.id.cancelButton);
//
//                cancelButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//
//                //QR SCANNER CODE
//
//                BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(view.getContext())
//                        .setBarcodeFormats(Barcode.QR_CODE)
//                        .build();
//                barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
//                    @Override
//                    public void release() {
//                    }
//
//                    @Override
//                    public void receiveDetections(Detector.Detections<Barcode> detections) {
//
//                        final SparseArray<Barcode> barcodes = detections.getDetectedItems();
//
//                        if (barcodes.size() != 0) {
//                            barcodeInfo.post(new Runnable() {    // Use the post method of the TextView
//                                public void run() {
//                                    barcodeInfo.setText(    // Update the TextView
//                                            barcodes.valueAt(0).displayValue
//                                    );
//
//                                    getPrecriptionDB();
//
//                                    dialog.dismiss();
//                                }
//                            });
//                        }
//
//                    }
//                });
//
//                final CameraSource cameraSource = new CameraSource
//                        .Builder(view.getContext(), barcodeDetector)
//                        .setRequestedPreviewSize(640, 480)
//                        .build();
//
//                cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
//                    @Override
//                    public void surfaceCreated(SurfaceHolder holder) {
//                        try {
//                            cameraSource.start(cameraView.getHolder());
//                        } catch (IOException ie) {
//                            Log.e("CAMERA SOURCE", ie.getMessage());
//                        }
//                    }
//
//                    @Override
//                    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//                    }
//
//                    @Override
//                    public void surfaceDestroyed(SurfaceHolder holder) {
//                        cameraSource.stop();
//                    }
//                });
//
//                dialog.show();
//            }
//        });
//
//    }
//
//    private void getPrecriptionDB(){
//        PD.show();
//        JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            int success = response.getInt("success");
//
//                            if (success == 1) {
//                                JSONArray ja = response.getJSONArray(RESPONSE);
//
//                                for (int i = 0; i < ja.length(); i++) {
//
//                                    JSONObject jobj = ja.getJSONObject(i);
//
//                                    HashMap<String, String> item = new HashMap<String, String>();
//                                    item.put(PRESCRIPTON_NO, jobj.getString(PRESCRIPTON_NO));
//                                    item.put(PATIENT_PHONE, jobj.getString(PATIENT_PHONE));
//                                    item.put(DRUG_ID, jobj.getString(DRUG_ID));
//                                    //item.put(DRUG_DAYS, jobj.getString(DRUG_DAYS));
//                                    //item.put(DRUG_P_DAY, jobj.getString(DRUG_P_DAY));
//                                    //item.put(DATE_ISSUE, jobj.getString(DATE_ISSUE));
//                                    //item.put(DATE_END, jobj.getString(DATE_END));
//                                    item.put(DATE_TAKEN, jobj.getString(DATE_TAKEN));
//
//                                    Item_List.add(item);
//
//                                } // for loop ends
//
////                                String[] from = { FRIEND_ID, FRIEND_NAME, FRIEND_LATITUDE, FRIEND_LONGITUDE, FRIEND_SPEED, FRIEND_DATETIME };
////                                int[] to = { R.id.item_id, R.id.item_name, R.id.item_latitude, R.id.item_longitude, R.id.item_speed, R.id.item_datetime  };
////
////                                adapter = new SimpleAdapter(
////                                        getApplicationContext(), Item_List,
////                                        R.layout.list_items, from, to);
////
////                                setListAdapter(adapter);
//
//                                Toast.makeText(getApplication(), Item_List.toString(), Toast.LENGTH_LONG).show();
//                                barcodeInfo.setText(Item_List.toString());
//                                PD.dismiss();
//
//                            } // if ends
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                PD.dismiss();
//            }
//        });
//
//        // Adding request to request queue
//        jreq.setShouldCache(false);
//        VolleyDB.getInstance().addToReqQueue(jreq);
//
//    }
//
//}
