package projectPSC.resources;



import projectPSC.db.MySQL;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;


@Path("/postOffice")
public class getInfo
{
    @GET
    @Path("/city/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPSC(@PathParam("city")String City)throws SQLException
    {

        List<String> list = new MySQL().getCity(City);


        boolean b = false;

        String result = "{\"psc\":[";
        for (String temp : list) {
            if (b == true) {
                result += ',';
            } else
                b = true;
            result += "\"" + temp + "\"";

        }
        result += "]})";



    return  result;
    }

    @GET
    @Path("/psc/{psc}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCityByPSC(@PathParam("psc") String PSC)throws SQLException
    {
        List<String> list2 = new MySQL().getCityFromPSC(PSC);


        boolean b = false;

        String result = "{\"city\":[";
        for (String temp : list2) {
            if (b == true) {
                result += ',';
            } else
                b = true;
            result += "\"" + temp + "\"";

        }
        result += "]})";



        return  result;
    }

}