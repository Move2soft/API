package com.wanttomeet.uv.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.Base64;
import com.loopj.android.http.RequestParams;
import com.wanttomeet.uv.Listener.ResponseListener;
import com.wanttomeet.uv.R;
import com.wanttomeet.uv.adapter.SelectImageAdapter;
import com.wanttomeet.uv.model.Profile;
import com.wanttomeet.uv.utils.AsyncHttpRequest;
import com.wanttomeet.uv.utils.BaseActivity;
import com.wanttomeet.uv.utils.Constants;
import com.wanttomeet.uv.utils.Debug;
import com.wanttomeet.uv.utils.RequestParamsUtils;
import com.wanttomeet.uv.utils.ResponseHandler;
import com.wanttomeet.uv.utils.URLs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterTwo extends BaseActivity implements ResponseListener {

    @Bind(R.id.tvChangeImage)
    TextView tvChangeImage;

    @Bind(R.id.tvAddImage)
    TextView tvAddImage;

    @Bind(R.id.ivProfile)
    ImageView ivProfile;

    @Bind(R.id.etHigherEduction)
    EditText etHigherEduction;

    @Bind(R.id.etCollage)
    EditText etCollage;

    @Bind(R.id.etOccupation)
    EditText etOccupation;

    @Bind(R.id.etOccuptionDetail)
    EditText etOccuptionDetail;

    @Bind(R.id.etAboutMe)
    EditText etAboutMe;

    @Bind(R.id.rbGov)
    RadioButton rbGov;

    @Bind(R.id.rbBuisness)
    RadioButton rbBuisness;

    @Bind(R.id.rbPrivate)
    RadioButton rbPrivate;

    @Bind(R.id.rbSelf)
    RadioButton rbSelf;

    @Bind(R.id.rbNotWorking)
    RadioButton rbNotWorking;

    List<RadioButton> employye = new ArrayList<RadioButton>();
    public AlertDialog alertDialog;
    int REQUEST_CAMERA = 541;
    int SELECT_IMAGE;
    private String url;
    private Bundle bundle;
    private String name, mobileno, gender, dob, email, password, city, state, country, employee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_two);
        ButterKnife.bind(this);
        getIntentData();
        addButton();
        setRadioButton();
    }

    public void getIntentData() {
        bundle = getIntent().getExtras();
        if (bundle != null) {
            name = bundle.getString("name");
            mobileno = bundle.getString("mobileno");
            gender = bundle.getString("gender");
            dob = bundle.getString("dob");
            email = bundle.getString("email");
            password = bundle.getString("password");
            city = bundle.getString("city");
            state = bundle.getString("state");
            country = bundle.getString("country");
        }
    }

    public void addButton() {
        employye.add(rbGov);
        employye.add(rbPrivate);
        employye.add(rbBuisness);
        employye.add(rbSelf);
        employye.add(rbNotWorking);

    }

    public void setRadioButton() {
        for (RadioButton button : employye) {

            button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) processRadioButtonClick(buttonView);
                }
            });

        }
    }

    @OnClick(R.id.tvSubmit)
    public void tvSubmitClick() {
        doRegistration();
    }

    private void processRadioButtonClick(CompoundButton buttonView) {

        for (RadioButton button : employye) {

            if (button != buttonView) button.setChecked(false);
            else {
                employee = button.getText().toString();
            }
        }

    }


    @OnClick(R.id.tvAddImage)
    public void addImageClick() {
        selectImage(Arrays.asList("Take Photo", "Choose from Gallery", "Cancel"));

    }

    @OnClick(R.id.ivProfile)
    public void addimag(){
        selectImage(Arrays.asList("Take Photo", "Choose from Gallery", "Cancel"));
    }

    @OnClick(R.id.tvChangeImage)
    public void tvChangeImage() {
        selectImage(Arrays.asList("Take Photo", "Choose from Gallery",
                "Cancel"));
    }

    private void selectImage(final List<String> list) {


        LayoutInflater li = LayoutInflater.from(this);

        final View confirmDialog = li.inflate(R.layout.category_dialog, null);

        TextView tv_dialog_title = (TextView) confirmDialog.findViewById(R.id.tv_dialog_title);
        tv_dialog_title.setText("Select Image");

        final ListView listview_category_list = (ListView) confirmDialog.findViewById(R.id.listview_category_list);
        SelectImageAdapter spinnerAdapter = new SelectImageAdapter(this);
        spinnerAdapter.addAll(list);
        listview_category_list.setAdapter(spinnerAdapter);

        listview_category_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (list.get(i).equals("Take Photo")) {
                    alertDialog.dismiss();
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (list.get(i).equals("Choose from Gallery")) {
                    alertDialog.dismiss();
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, SELECT_IMAGE);
                } else if (list.get(i).equals("Cancel")) {
                    alertDialog.dismiss();
                }
            }
        });


        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setView(confirmDialog);

        alertDialog = alert.create();

        alertDialog.show();

        alertDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_IMAGE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        url = getStringImage(thumbnail);
        Log.e("base url is:", url);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ivProfile.setImageBitmap(thumbnail);

    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Uri selectedImageUri = data.getData();
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
                null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();

        String selectedImagePath = cursor.getString(column_index);

        Bitmap bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, options);
        final int REQUIRED_SIZE = 200;
        int scale = 1;
        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(selectedImagePath, options);
        ivProfile.setImageBitmap(bm);
        url = getStringImage(bm);
        if (url != null) {
            tvAddImage.setVisibility(View.GONE);
        } else {
            tvAddImage.setVisibility(View.VISIBLE);
        }
        Log.e("base url is:", url);

    }

    public String getStringImage(Bitmap bmp) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 60, baos);
            byte[] imageBytes = baos.toByteArray();
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            return encodedImage;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void doRegistration() {
        try {
            showProgress("");
            RequestParams params = new RequestParams();
            params.put(RequestParamsUtils.MID, "");
            params.put(RequestParamsUtils.MNAME, name);
            params.put(RequestParamsUtils.MGENDER, gender);
            params.put(RequestParamsUtils.MCONTACT, mobileno);
            params.put(RequestParamsUtils.MDOB, dob);
            params.put(RequestParamsUtils.MPASSWORD, password);
            params.put(RequestParamsUtils.MEMAIL, email);
            params.put(RequestParamsUtils.MPASSWORD, password);
            params.put(RequestParamsUtils.MCITY, city);
            params.put(RequestParamsUtils.MSTATE, state);
            params.put(RequestParamsUtils.MCOUNTRY, country);
            params.put(RequestParamsUtils.MEDUCTION, etHigherEduction.getText().toString());
            params.put(RequestParamsUtils.MCOLLAGE, etCollage.getText().toString());
            params.put(RequestParamsUtils.MEMPLOUEDIN, employee);
            params.put(RequestParamsUtils.MOCCUPTION, etOccupation.getText().toString());
            params.put(RequestParamsUtils.MOCCUPTIONDETAIL, etOccuptionDetail.getText().toString());
            params.put(RequestParamsUtils.MABOUT, etAboutMe.getText().toString());
            params.put(RequestParamsUtils.MTOKEN, "");
            params.put(RequestParamsUtils.MLAT, "");
            params.put(RequestParamsUtils.MLONG, "");
            params.put(RequestParamsUtils.MIMAGE, url);
            params.put(RequestParamsUtils.MIsPhoneAllow, true);

            Log.e("doRegistration", url);

            Debug.e("doRegistration", params.toString());

            AsyncHttpClient clientPhotos = AsyncHttpRequest.newRequest();

            clientPhotos.post(new URLs().REGISTER, params, new ResponseHandler(RegisterTwo.this, this));

        } catch (Exception e) {
            Debug.e("doRegistration Exception", e.getMessage());
        }
    }


    @Override
    public void onResponse(String response) {
        dismissProgress();
        if (response != null && response.length() > 0) {
            try {
                dismissProgress();
                Log.e("doRegistration", response);

                if (response != null && response.length() > 0) {

                    Profile pt = new Gson().fromJson(
                            response, new TypeToken<Profile>() {
                            }.getType());

                    if (pt.st == 1) {
                        Constants.PROFILE = pt;
                        SharedPreferences.Editor editor = getPreferences().edit();
                        editor.putString(RequestParamsUtils.USERPROFILE, response);
                        editor.commit();
                        Intent intent = new Intent(RegisterTwo.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getActivity(), "Invild Data !...Try Again...!", Toast.LENGTH_LONG).show();
                    }

                }

            } catch (Exception e) {
                Debug.e("RetryHandler Sucess Exception", e.getMessage());
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
