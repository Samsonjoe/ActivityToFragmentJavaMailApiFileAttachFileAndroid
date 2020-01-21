package com.wiz.mailattachfileandroid.fragments;

    import android.app.ProgressDialog;
    import android.content.Intent;
    import android.database.Cursor;
    import android.net.Uri;
    import android.os.Bundle;
    import android.provider.MediaStore;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.ListView;
    import android.widget.ProgressBar;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;
    import androidx.fragment.app.Fragment;

    import com.wiz.mailattachfileandroid.utils.JavaMailAPIattachment;
    import com.wiz.mailattachfileandroid.R;

    import java.util.ArrayList;
    import java.util.HashMap;

    import static android.app.Activity.RESULT_OK;

    public class DialogClaimRequestform extends Fragment {


    private TextView textViewClaims_Policy_number_DIALOGCLAIMREQUEST,textViewClaims_Account_name_DIALOGCLAIMREQUEST,textViewClaims_Policy_end_DIALOGCLAIMREQUEST;
    private String TAG = DialogClaimRequestform.class.getSimpleName();
    private ListView lv;



    private static final String KEY_EMPTY = "";


    String uploadFileName;
    //String uploadFilePath;


    ArrayList<HashMap<String, String>> claimListInclaims;

    EditText editTextshowFilePickedPath;
    String FilePickedPathString;

    ProgressDialog dialog = null;
    String upLoadServerUri = null;
    int serverResponseCode = 0;
    ProgressBar spinner;

    Uri URI = null;
    int columnIndex;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.dialog_claim_request_form, container, false);


        editTextshowFilePickedPath = view.findViewById(R.id.EdittextClaimFilePath);





        Button btnPlaceClaim = view.findViewById(R.id.btnPlaceYourRequest);
        btnPlaceClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Retrieve the data entered in the edit texts
                FilePickedPathString = editTextshowFilePickedPath.getText().toString().trim();
                if (validateInputs()) {
                    sendMail();

                    editTextshowFilePickedPath.getText().clear();
                }

            }
        });


        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button btnChooseClaimFile = view.findViewById(R.id.buttonChooseFileClaim);
        btnChooseClaimFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);

            }
        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub


        if(resultCode==RESULT_OK){

            Uri selectedImmage = data.getData();
            String[] filePathColumn ={MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(selectedImmage,filePathColumn,null,null,null);
            // columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            uploadFileName = cursor.getString(column_index);
            // Log.e("Attachment Path:",uploadFileName);

            URI = Uri.parse("file://"+uploadFileName);
            cursor.close();



            //uploadFileName =  data.getData().getPath();
            // uploadFilePath = data.getData().getPath();


            editTextshowFilePickedPath.setText(uploadFileName);
            Toast.makeText(getActivity(), uploadFileName , Toast.LENGTH_LONG).show();

        }
    }



    private void sendMail() {


        // String mail = emailSignup.getText().toString().trim();

        String mail = "samnjoroge6035@gmail.com";
        String Subject = "TEST IMAGES";
        String SubjectMain= "CLAIM POLICY REQUEST: "+Subject;
        String DescriptionOfClaimFromUser ="pLEASE WORK";
        String filePathMail =uploadFileName;
        String EmailCancelPolicyDetails =DescriptionOfClaimFromUser+" \n\n The policy details are as follows\n\nClient Phone Number:  \n\nRegards,\nSam";


        //send Mail
        JavaMailAPIattachment JavaMailAPIattachment = new JavaMailAPIattachment(getActivity(),mail,SubjectMain,EmailCancelPolicyDetails,filePathMail);
        JavaMailAPIattachment.execute();
    }

    /**
     * Validates inputs and shows error if any
     * @return
     */
    private boolean validateInputs() {


        if(KEY_EMPTY.equals(FilePickedPathString)){
            editTextshowFilePickedPath.requestFocus();
            Toast.makeText(getActivity(), "Kindly attach a document file", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}


