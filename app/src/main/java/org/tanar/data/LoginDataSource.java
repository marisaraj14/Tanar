
package org.tanar.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import org.tanar.R;
import org.tanar.data.model.LoggedInUser;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.io.IOException;

/**;
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private static final String TAG = "DataSource";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public void login(String username, String password, MutableLiveData<LoginResult> loginResult) {

        db.collection("Users").whereEqualTo("username", username).whereEqualTo("password", password)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && !task.getResult().isEmpty()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                LoggedInUser data =
                                        new LoggedInUser(document.getId()
                                                , document.get("firstName").toString()
                                                + " " + document.get("lastName").toString()
                                        );
                                loginResult.setValue(new LoginResult(data.getDisplayName()));
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            loginResult.setValue(new LoginResult(R.string.login_failed));
                        }
                    }
                });

    }

    public void logout() {
        // TODO: revoke authentication
    }
}