package edu.uml.nateg.database2project;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.util.Log;

import java.util.ArrayList;


public class MainActivity extends ListActivity {

    String mUsername = "";
    String mPassword = "";
    String mConnectionURL = "";
    HTTPConnection mConnection = new HTTPConnection();
    boolean connected = false;
    boolean block = false;

    ArrayList<String> results = new ArrayList<String>();
    ArrayList<String> queries = new ArrayList<String>();

    String firstName = "";
    String lastName = "";
    String year = "";
    String teamName = "";
    String awardID = "";

    private class HTTPTask extends AsyncTask<String, String, ArrayList<String>>
    {
        protected ArrayList<String> doInBackground(String... data)
        {
            try
            {
                while (block) {} //let user input data
                return mConnection.executeQuery(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], data[5]);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<String> results)
        {
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("results", results);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        queries.add("Get the names of current teams");
        queries.add("Get the team a player plays for");
        queries.add("Get player basic information");
        queries.add("Get batting statistics for a player");
        queries.add("Get pitching statistics for a player");
        queries.add("Get fielding statistics for a player");
        queries.add("Get the manager for a team");
        queries.add("Get salary for a player or manager");
        queries.add("Get the school a player played for");
        queries.add("Get the awards given in a certain year");
        queries.add("Get the awards given to a certain player");
        queries.add("Get all players who have received a certain award");

        log("Populated list");

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,
                R.layout.row_layout, R.id.listText, queries);
        setListAdapter(myAdapter);

        RelativeLayout headerView = (RelativeLayout) getLayoutInflater()
                .inflate(R.layout.activity_main, getListView(), false);
        getListView().addHeaderView(headerView);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //execute that query
                LayoutInflater inflater = getLayoutInflater();
                final View dialogLayout;
                AlertDialog dialog;
                block = true;

                try{
                    switch(position)
                    {
                        case 1:
                            //Display the input dialog
                            //      no dialog for this one
                            //Get the input strings
                            //execute the query
                            block = false;
                            new HTTPTask().execute("1", null, null, null, null, null);
                            break;
                        case 2:
                            //Display the input dialog
                            dialogLayout = inflater.inflate(R.layout.query2_layout, null);
                            dialog = new AlertDialog.Builder(MainActivity.this)
                                    .setView(dialogLayout)
                                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            EditText firstNameView;
                                            EditText lastNameView;
                                            EditText yearView;

                                            //Get the input strings
                                            firstNameView = (EditText) dialogLayout.findViewById(R.id.query2_firstName);
                                            firstName = firstNameView.getText().toString();
                                            lastNameView = (EditText) dialogLayout.findViewById(R.id.query2_lastName);
                                            lastName = lastNameView.getText().toString();
                                            yearView = (EditText) dialogLayout.findViewById(R.id.query2_year);
                                            year = yearView.getText().toString();
                                            block = false;
                                        }
                                    })
                                    .show();

                            //execute the query
                            new HTTPTask().execute("2", firstName, lastName, year, null, null);
                            break;
                        case 3:
                            //Display the input dialog
                            dialogLayout = inflater.inflate(R.layout.query3_layout, null);
                            dialog = new AlertDialog.Builder(MainActivity.this)
                                    .setView(dialogLayout)
                                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            EditText firstNameView;
                                            EditText lastNameView;

                                            //Get the input strings
                                            firstNameView = (EditText) dialogLayout.findViewById(R.id.query3_firstName);
                                            firstName = firstNameView.getText().toString();
                                            lastNameView = (EditText) dialogLayout.findViewById(R.id.query3_lastName);
                                            lastName = lastNameView.getText().toString();
                                            block = false;
                                        }
                                    })
                                    .show();
                            //Get the input strings
                            //execute the query
                            new HTTPTask().execute("3", firstName, lastName, null, null, null);
                            break;
                        case 4:
                            //Display the input dialog
                            dialogLayout = inflater.inflate(R.layout.query4_layout, null);
                            dialog = new AlertDialog.Builder(MainActivity.this)
                                    .setView(dialogLayout)
                                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            EditText firstNameView;
                                            EditText lastNameView;
                                            EditText yearView;

                                            //Get the input strings
                                            firstNameView = (EditText) dialogLayout.findViewById(R.id.query4_firstName);
                                            firstName = firstNameView.getText().toString();
                                            lastNameView = (EditText) dialogLayout.findViewById(R.id.query4_lastName);
                                            lastName = lastNameView.getText().toString();
                                            yearView = (EditText) dialogLayout.findViewById(R.id.query4_year);
                                            year = yearView.getText().toString();
                                            block = false;
                                        }
                                    })
                                    .show();
                            //Get the input strings
                            //execute the query
                            new HTTPTask().execute("4", firstName, lastName, year, null, null);
                            break;
                        case 5:
                            //Display the input dialog
                            dialogLayout = inflater.inflate(R.layout.query5_layout, null);
                            dialog = new AlertDialog.Builder(MainActivity.this)
                                    .setView(dialogLayout)
                                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            EditText firstNameView;
                                            EditText lastNameView;
                                            EditText yearView;

                                            //Get the input strings
                                            firstNameView = (EditText) dialogLayout.findViewById(R.id.query5_firstName);
                                            firstName = firstNameView.getText().toString();
                                            lastNameView = (EditText) dialogLayout.findViewById(R.id.query5_lastName);
                                            lastName = lastNameView.getText().toString();
                                            yearView = (EditText) dialogLayout.findViewById(R.id.query5_year);
                                            year = yearView.getText().toString();
                                            block = false;
                                        }
                                    })
                                    .show();
                            //Get the input strings
                            //execute the query
                            new HTTPTask().execute("5", firstName, lastName, year, null, null);
                            break;
                        case 6:
                            //Display the input dialog
                            dialogLayout = inflater.inflate(R.layout.query6_layout, null);
                            dialog = new AlertDialog.Builder(MainActivity.this)
                                    .setView(dialogLayout)
                                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            EditText firstNameView;
                                            EditText lastNameView;
                                            EditText yearView;

                                            //Get the input strings
                                            firstNameView = (EditText) dialogLayout.findViewById(R.id.query6_firstName);
                                            firstName = firstNameView.getText().toString();
                                            lastNameView = (EditText) dialogLayout.findViewById(R.id.query6_lastName);
                                            lastName = lastNameView.getText().toString();
                                            yearView = (EditText) dialogLayout.findViewById(R.id.query6_year);
                                            year = yearView.getText().toString();
                                            block = false;
                                        }
                                    })
                                    .show();
                            //Get the input strings
                            //execute the query
                            new HTTPTask().execute("6", firstName, lastName, year, null, null);
                            break;
                        case 7:
                            //Display the input dialog
                            dialogLayout = inflater.inflate(R.layout.query7_layout, null);
                            dialog = new AlertDialog.Builder(MainActivity.this)
                                    .setView(dialogLayout)
                                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            EditText yearView;
                                            EditText teamNameView;

                                            //Get the input strings
                                            teamNameView = (EditText) dialogLayout.findViewById(R.id.query7_teamID);
                                            firstName = teamNameView.getText().toString();
                                            yearView = (EditText) dialogLayout.findViewById(R.id.query7_year);
                                            year = yearView.getText().toString();
                                            block = false;
                                        }
                                    })
                                    .show();
                            //Get the input strings
                            //execute the query
                            new HTTPTask().execute("7", null, null, year, teamName, null);
                            break;
                        case 8:
                            //Display the input dialog
                            dialogLayout = inflater.inflate(R.layout.query8_layout, null);
                            dialog = new AlertDialog.Builder(MainActivity.this)
                                    .setView(dialogLayout)
                                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            EditText firstNameView;
                                            EditText lastNameView;
                                            EditText yearView;

                                            //Get the input strings
                                            firstNameView = (EditText) dialogLayout.findViewById(R.id.query8_firstName);
                                            firstName = firstNameView.getText().toString();
                                            lastNameView = (EditText) dialogLayout.findViewById(R.id.query8_lastName);
                                            lastName = lastNameView.getText().toString();
                                            yearView = (EditText) dialogLayout.findViewById(R.id.query8_year);
                                            year = yearView.getText().toString();
                                            block = false;
                                        }
                                    })
                                    .show();
                            //Get the input strings
                            //execute the query
                            new HTTPTask().execute("8", firstName, lastName, year, null, null);
                            break;
                        case 9:
                            //Display the input dialog
                            dialogLayout = inflater.inflate(R.layout.query9_layout, null);
                            dialog = new AlertDialog.Builder(MainActivity.this)
                                    .setView(dialogLayout)
                                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            EditText firstNameView;
                                            EditText lastNameView;

                                            //Get the input strings
                                            firstNameView = (EditText) dialogLayout.findViewById(R.id.query9_firstName);
                                            firstName = firstNameView.getText().toString();
                                            lastNameView = (EditText) dialogLayout.findViewById(R.id.query9_lastName);
                                            lastName = lastNameView.getText().toString();
                                            block = false;
                                        }
                                    })
                                    .show();
                            //Get the input strings
                            //execute the query
                            new HTTPTask().execute("9", firstName, lastName, null, null, null);
                            break;
                        case 10:
                            //Display the input dialog
                            dialogLayout = inflater.inflate(R.layout.query10_layout, null);
                            dialog = new AlertDialog.Builder(MainActivity.this)
                                    .setView(dialogLayout)
                                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            EditText yearView;

                                            //Get the input strings
                                            yearView = (EditText) dialogLayout.findViewById(R.id.query10_year);
                                            year = yearView.getText().toString();
                                            block = false;
                                        }
                                    })
                                    .show();
                            //Get the input strings
                            //execute the query
                            new HTTPTask().execute("10", null, null, year, null, null);
                            break;
                        case 11:
                            //Display the input dialog
                            dialogLayout = inflater.inflate(R.layout.query11_layout, null);
                            dialog = new AlertDialog.Builder(MainActivity.this)
                                    .setView(dialogLayout)
                                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            EditText firstNameView;
                                            EditText lastNameView;

                                            //Get the input strings
                                            firstNameView = (EditText) dialogLayout.findViewById(R.id.query11_firstName);
                                            firstName = firstNameView.getText().toString();
                                            lastNameView = (EditText) dialogLayout.findViewById(R.id.query11_lastName);
                                            lastName = lastNameView.getText().toString();
                                            block = false;
                                        }
                                    })
                                    .show();
                            //Get the input strings
                            //execute the query
                            
                            new HTTPTask().execute("11", firstName, lastName, null, null, null);
                            break;
                        case 12:
                            //Display the input dialog
                            dialogLayout = inflater.inflate(R.layout.query12_layout, null);
                            dialog = new AlertDialog.Builder(MainActivity.this)
                                    .setView(dialogLayout)
                                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            EditText awardIDView;
                                            //Get the input strings
                                            awardIDView = (EditText) dialogLayout.findViewById(R.id.query12_awardID);
                                            year = awardIDView.getText().toString();
                                            block = false;
                                        }
                                    })
                                    .show();
                            //Get the input strings
                            //execute the query
                            new HTTPTask().execute("12", null, null, null, null, awardID);
                            break;
                    }

                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        log("Set up list");

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.login_dialog, null);
        builder.setView(dialogView)
                .setPositiveButton(R.string.sign_in, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //sign in
                        EditText userView = (EditText) dialogView.findViewById(R.id.username);
                        mUsername = userView.getText().toString();
                        EditText passView = (EditText) dialogView.findViewById(R.id.password);
                        mPassword = passView.getText().toString();
                        EditText urlView = (EditText) dialogView.findViewById(R.id.url);
                        mConnectionURL = urlView.getText().toString();
                        mConnection.setHost(mConnectionURL);

                        new Thread()
                        {
                            public void run()
                            {
                                try
                                {
                                    if (mConnection.loginToServer(mUsername, mPassword) != -1)
                                    {
                                        //assumes localhost
                                        connected = true;
                                    }
                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //cancel
                    }
                });

        builder.show();

        log("Finished onCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void log(String msg) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("DB2Project", msg);
    }
}
