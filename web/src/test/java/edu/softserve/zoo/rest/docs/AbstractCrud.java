package edu.softserve.zoo.rest.docs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.Map;
import java.util.Objects;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Abstract class for basic http request types testing
 *
 * @author Taras Zubrei
 */
public abstract class AbstractCrud extends AbstractTest {
    @Autowired
    ObjectMapper objectMapper;

    /**
     * {@code GET} request test
     */
    protected void getOp() throws Exception {
        RestDocumentationResultHandler document = documentPrettyPrintReqResp("get" + getName());
        document.snippets(responseFields(getFields(true, true)));
        this.mockMvc.perform(get(getPath())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document);
    }

    /**
     * {@code GET} request test
     *
     * @param object map that represents the dto
     */
    protected void postOp(Map<String, Object> object) throws Exception {
        RestDocumentationResultHandler document = documentPrettyPrintReqResp("post" + getName());
        document.snippets(
                requestFields(getFields(false, false)),
                responseFields(getFields(false, true)));
        this.mockMvc.perform(post(getPath())
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(object)))
                .andExpect(status().isOk())
                .andDo(document);
    }

    /**
     * {@code PATCH} request test
     *
     * @param object map that represents the dto
     * @param id     id of entity to update
     */
    protected void patchOp(Map<String, Object> object, Long id) throws Exception {
        RestDocumentationResultHandler document = documentPrettyPrintReqResp("patch" + getName());
        document.snippets(
                pathParameters(parameterWithName("id").description("id of " + StringUtils.uncapitalize(getName()))),
                requestFields(getFields(false, false, object)),
                responseFields(getFields(false, true)));
        this.mockMvc.perform(patch(getPath() + "/{id}", id)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(object)))
                .andExpect(status().isOk())
                .andDo(document);
    }

    /**
     * {@code PUT} request test
     *
     * @param object map that represents the dto
     * @param id     id of entity to update
     */
    protected void putOp(Map<String, Object> object, Long id) throws Exception {
        RestDocumentationResultHandler document = documentPrettyPrintReqResp("put" + getName());
        document.snippets(
                pathParameters(parameterWithName("id").description("id of " + StringUtils.uncapitalize(getName()))),
                requestFields(getFields(false, false)),
                responseFields(getFields(false, true)));
        this.mockMvc.perform(put(getPath() + "/{id}", id)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(object)))
                .andExpect(status().isOk())
                .andDo(document);
    }

    /**
     * {@code DELETE} request test
     *
     * @param id id of entity to delete
     */
    protected void deleteOp(Long id) throws Exception {
        RestDocumentationResultHandler document = documentPrettyPrintReqResp("delete" + StringUtils.uncapitalize(getName()));
        document.snippets(pathParameters(parameterWithName("id").description("id of " + getName())));
        this.mockMvc.perform(delete(getPath() + "/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document);
    }

    /**
     * Returns the REST url
     *
     * @return path url
     */
    protected abstract String getPath();

    /**
     * Returns the map of field name as key and its description as value
     *
     * @return map of fields and description
     */
    protected abstract Map<String, String> getFieldsWithDescription();

    /**
     * Returns the unique name for folder with snippets
     *
     * @return unique folder name
     */
    protected abstract String getName();

    /**
     * Returns array of FieldDescriptor which contains fields with description of request/response body.
     * The result may include {@code id} field, e.g. in {@code POST} response, or may not, e.g. in {@code PATCH} request.
     * The result may be array of objects , e.g. in {@code GET} response, or may not, e.g. in {@code PUT} request.
     * In case of {@code PATCH} request not all fields may be included.
     * In order to provide this the method may return only those fields which request object contains.
     *
     * @param isArray whether it is array or not
     * @param withId  whether it includes id or not
     * @param object  object fields will be compared with
     * @return complete array of field descriptors
     */
    @SafeVarargs
    private final FieldDescriptor[] getFields(boolean isArray, boolean withId, Map<String, Object>... object) {
        return getFieldsWithDescription()
                .entrySet()
                .stream()
                .filter(entry -> withId || !Objects.equals(entry.getKey(), "id"))
                .filter(entry -> object.length == 0 || object[0].containsKey(entry.getKey()))
                .map(entry -> fieldWithPath((isArray ? "[]." : "") + entry.getKey()).description(entry.getValue()))
                .toArray(FieldDescriptor[]::new);
    }
}
