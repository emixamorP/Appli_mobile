package com.example.festival;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;

import com.example.festival.dao.UserDao;
import com.example.festival.entity.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;
    private Context context;
    private ActivityResultLauncher<Intent> editUserActivityResultLauncher;
    public UserAdapter(List<User> userList, Context context, ActivityResultLauncher<Intent> editUserActivityResultLauncher) {
        this.userList = userList ;
        this.context = context ;
        this.editUserActivityResultLauncher= editUserActivityResultLauncher ;
    }

    // Constructeur pour initialiser la liste et le contexte

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view, this.editUserActivityResultLauncher);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.textViewNom.setText(user.getNom());
        holder.textViewPrenom.setText(user.getPrenom());
        // Définissez les listeners pour les icônes de suppression et de modification ici

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setUserList(List<User> updatedUserList) {
        this.userList =updatedUserList ;
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNom, textViewPrenom;
        ImageView imageViewDelete, imageViewEdit;
        ActivityResultLauncher<Intent> editUserActivityResultLauncher;
        UserViewHolder(View itemView,  ActivityResultLauncher<Intent> editUserActivityResultLauncher) {
            super(itemView);
            textViewNom = itemView.findViewById(R.id.textViewNom);
            textViewPrenom = itemView.findViewById(R.id.textViewPrenom);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            imageViewEdit = itemView.findViewById(R.id.imageViewEdit);
            this.editUserActivityResultLauncher = editUserActivityResultLauncher;
            // Définissez les actions pour imageViewDelete et imageViewEdit ici
            imageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position =  getAbsoluteAdapterPosition();
                    User user = userList.get(position);
                    // Supprimez l'utilisateur de votre base de données
                    // Mettez à jour la liste des utilisateurs et notifiez l'adaptateur
                    UserDao UserDao = new UserDao();
                    UserDao.deleteUser(user.getId());
                    userList.remove(position);
                    notifyItemRemoved(position);
                }
            });
            imageViewEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAbsoluteAdapterPosition();
                    User userToEdit = userList.get(position);

                    Intent editIntent = new Intent(context, EditUserActivity.class);
                    // Passer les informations de l'utilisateur à EditUserActivity
                    // Vous pouvez passer l'ID de l'utilisateur ou sérialiser l'objet User
                    editIntent.putExtra("user_id", userToEdit); // Exemple si vous passez juste l'ID
                    // Ou en utilisant Parcelable/Serializable pour passer l'objet complet
                    // editIntent.putExtra("user", userToEdit);
                    editUserActivityResultLauncher.launch(editIntent);
                    context.startActivity(editIntent);
                }
            });


        }
    }
}
