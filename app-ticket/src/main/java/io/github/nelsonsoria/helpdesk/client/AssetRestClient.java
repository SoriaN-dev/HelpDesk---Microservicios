package io.github.nelsonsoria.helpdesk.client;


import io.github.nelsonsoria.helpdesk.dto.asset.AssetDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/assets")
@RegisterRestClient(baseUri = "stork://assets-api")
public interface AssetRestClient {

    @GET
    @Path("/{assetId}")
    AssetDTO getAssetById(Long assetId);
}
