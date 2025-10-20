package br.com.LucasNog21.School.controller.docs;

import br.com.LucasNog21.School.dto.security.AccountCredentialsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface AuthControllerDocs {

    @Operation(
            summary = "Autentica um usuário e retorna um token",
            description = "Valida credenciais de usuário e gera um token para autenticaçaõ",
            tags = {"Autenticação"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<?> signin(AccountCredentialsDTO credentials);

    @Operation(
            summary = "Atualiza token para autenticação e retorna o token",
            description = "Gera um novo token usando a atualização de um token e usuário",
            tags = {"Autenticação"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<?> refreshToken(
            String username,
            String refreshToken);

    @Operation(
            summary = "Cria um novo usuário",
            description = "Registra um novo usuário com as credenciais passadas",
            tags = {"Gestão de usuário"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    AccountCredentialsDTO create(AccountCredentialsDTO credentials);
}