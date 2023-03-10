
import ChessLogin.ChessLogin;
import restShared.AccountDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/auth")
public class ChessRESTservice {

    private ChessLogin chessLogin;

    public ChessRESTservice() {
        chessLogin = new ChessLogin();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(AccountDTO accountDTO){
        if(chessLogin.login(accountDTO.getUsername(), accountDTO.getPassword())){
            return Response.ok(RestResponseHelper.getSuccesResponse(), MediaType.APPLICATION_JSON).build();
        }
        return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(AccountDTO accountDTO){
        if(chessLogin.register(accountDTO.getUsername(),accountDTO.getPassword(), accountDTO.getEmail())){
            return Response.ok(RestResponseHelper.getSuccesResponse(), MediaType.APPLICATION_JSON).build();
        }else
            return Response.status(500).entity(RestResponseHelper.getErrorResponseString()).build();
    }
}
