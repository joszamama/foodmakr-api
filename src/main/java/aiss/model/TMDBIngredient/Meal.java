
package aiss.model.TMDBIngredient;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "strMeal",
    "strMealThumb",
    "idMeal"
})
public class Meal {

    @JsonProperty("strMeal")
    private String strMeal;
    @JsonProperty("strMealThumb")
    private String strMealThumb;
    @JsonProperty("idMeal")
    private String idMeal;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("strMeal")
    public String getStrMeal() {
        return strMeal;
    }

    @JsonProperty("strMeal")
    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    @JsonProperty("strMealThumb")
    public String getStrMealThumb() {
        return strMealThumb;
    }

    @JsonProperty("strMealThumb")
    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    @JsonProperty("idMeal")
    public String getIdMeal() {
        return idMeal;
    }

    @JsonProperty("idMeal")
    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
