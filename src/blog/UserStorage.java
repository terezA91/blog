package blog;

public class UserStorage {

    private User [] users = new User[10];
    private int size;

    public void add(User value){
        if(size == users.length){
            extend();
        }
        users[size++] = value;
    }

    private void extend(){
        User [] tmp = new User[users.length+5];
        System.arraycopy(users,0,tmp,0,users.length);
        users = tmp;
    }

     User getUserByEmailAndPassword(String email, String password){
        for (int i = 0; i <size; i++) {
            if (users[i].getEmail().equals(email) &&
                    users[i].getPassword().equals(password)) {
                return users[i];
            }
        }    return null;
    }
}
