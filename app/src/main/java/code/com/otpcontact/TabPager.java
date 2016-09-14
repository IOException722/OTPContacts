package code.com.otpcontact;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
public class TabPager extends FragmentStatePagerAdapter {

    int tabCount;
    public TabPager(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount= tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ContactList contactList = ContactList.newInstance("Strin1","String2");
                return contactList;
            case 1:
                SentList sentList = new SentList();
                return sentList;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
