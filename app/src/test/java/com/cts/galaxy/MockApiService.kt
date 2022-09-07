package com.cts.galaxy

import com.cts.galaxy.api.ApiService
import com.cts.galaxy.api.models.GalaxyDTO
import com.google.gson.Gson

class MockApiService : ApiService {
    override suspend fun getGalaxyCollection(
        baseQuery: String,
        mediaType: String,
        yearStart: String,
        yearEnd: String
    ): GalaxyDTO {
        return Gson().fromJson(mockRespose, GalaxyDTO::class.java)
    }


    companion object {
        fun getItem(): GalaxyDTO {
            return Gson().fromJson(mockRespose, GalaxyDTO::class.java)
        }

        private val mockRespose = "{\n" +
                "  \"collection\": {\n" +
                "    \"version\": \"1.0\",\n" +
                "    \"href\": \"http://images-api.nasa.gov/search?q=milky%20way&media_type=image&year_start=2017&\",\n" +
                "    \"items\": [\n" +
                "      {\n" +
                "        \"href\": \"https://images-assets.nasa.gov/image/PIA24576/collection.json\",\n" +
                "        \"data\": [\n" +
                "          {\n" +
                "            \"description\": \"A contingent of young stars and star-forming gas clouds is sticking out of one of the Milky Way's spiral arms like a splinter protruding from a plank of wood. Stretching some 3,000 light-years, this is the first major structure identified with such a dramatically different orientation relative to the arm.  The background image shows the location of the splinter in the Milky Way. The yellow region in the center of the image is the galaxy's bright and crowded center. The galaxy's arms spiral around the center, and are full of stars and star-forming clouds of gas and dust.  A key property of spiral arms is how tightly they wind around a galaxy. This characteristic is measured by the arm's pitch angle. A circle has a pitch angle of 0 degrees; as the spiral becomes more open, the pitch angle increases. Most models of the Milky Way suggest that the Sagittarius Arm forms a spiral that has a pitch angle of about 12 degrees, but the protruding structure has a pitch angle of nearly 60 degrees.  Similar structures – sometimes called spurs or feathers – are commonly found jutting out of the arms of other spiral galaxies. For decades scientists have wondered whether our Milky Way's spiral arms are also dotted with these structures or if they are relatively smooth.  https://photojournal.jpl.nasa.gov/catalog/PIA24576\",\n" +
                "            \"title\": \"A Break in the Milky Way's Sagittarius Arm\",\n" +
                "            \"nasa_id\": \"PIA24576\",\n" +
                "            \"date_created\": \"2021-08-17T00:00:00Z\",\n" +
                "            \"keywords\": [\n" +
                "              \"Milky Way\"\n" +
                "            ],\n" +
                "            \"media_type\": \"image\",\n" +
                "            \"description_508\": \"A contingent of stars and star-forming clouds was found jutting out from the Milky Way's Sagittarius Arm. The inset shows the size of the structure and distance from the Sun.\",\n" +
                "            \"secondary_creator\": \"NASA/JPL-Caltech\",\n" +
                "            \"center\": \"JPL\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"links\": [\n" +
                "          {\n" +
                "            \"href\": \"https://images-assets.nasa.gov/image/PIA24576/PIA24576~thumb.jpg\",\n" +
                "            \"rel\": \"preview\",\n" +
                "            \"render\": \"image\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}"
    }
}