package com.example.tooltip.data.repository

import com.assignment.tooltip.data.model.TooltipResponse
import com.example.tooltip.core.network.NetworkResult
import com.example.tooltip.domain.repository.TooltipRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class TooltipRepositoryImpl(
    private val gson: Gson
) : TooltipRepository {

    override suspend fun getTooltipConfig(): NetworkResult<TooltipResponse> {
        return withContext(Dispatchers.IO) {
            try {
                delay(800)

                val parsedData = gson.fromJson(RAW_JSON_DATA, TooltipResponse::class.java)

                NetworkResult.Success(parsedData)
            } catch (e: Exception) {
                NetworkResult.UnknownError
            }
        }
    }

    companion object {
        private const val RAW_JSON_DATA = """
            {
              "campaign_type": "TTP",
              "details": {
                "tooltips": [
                  {
                    "_id": "954b8bda-1687-49e7-9222-ed1a6cbca116",
                    "clickAction": "nextStep",
                    "ctaText": "25",
                    "enableBackdrop": true,
                    "eventName": "",
                    "link": "https://google,.com",
                    "order": 0,
                    "position": "",
                    "styling": {
                      "appearance": {
                        "arrowStyle": {
                          "height": 12,
                          "width": 6
                        },
                        "backdropOpacity": 62,
                        "colors": {
                          "arrow": "#fe6b35",
                          "backdrop": "#000000",
                          "tooltip": "#0000ff"
                        },
                        "cornerRadius": {
                          "bottomLeft": 12,
                          "bottomRight": 12,
                          "topLeft": 12,
                          "topRight": 12
                        },
                        "highlight": {
                          "padding": 4,
                          "radius": 12
                        },
                        "padding": {
                          "bottom": 8,
                          "left": 8,
                          "right": 8,
                          "top": 8
                        },
                        "width": null
                      },
                      "cta": {
                        "container": {
                          "alignment": "center",
                          "backgroundColor": "#f7921c",
                          "borderColor": "#fe6b35",
                          "borderWidth": 2,
                          "ctaFullWidth": false,
                          "ctaWidth": 120,
                          "height": 50
                        },
                        "cornerRadius": {
                          "bottomLeft": 12,
                          "bottomRight": 12,
                          "topLeft": 12,
                          "topRight": 12
                        },
                        "margin": {
                          "bottom": 4,
                          "left": 4,
                          "right": 4,
                          "top": 4
                        },
                        "text": {
                          "color": "#ffffff",
                          "fontFamily": "Arial",
                          "fontSize": 14
                        }
                      },
                      "subTitle": {
                        "color": "#ffffff",
                        "fontFamily": "Arial",
                        "fontSize": 12,
                        "margin": {
                          "bottom": 4,
                          "left": 4,
                          "right": 4,
                          "top": 4
                        },
                        "textAlign": "left"
                      },
                      "title": {
                        "color": "#f2f2f2",
                        "fontFamily": "https://cdn.appstorys.com/font/uploads/PlaywriteNZBasic-Regular_7027fc35_latin.ttf",
                        "fontSize": 14,
                        "margin": {
                          "bottom": 4,
                          "left": 4,
                          "right": 4,
                          "top": 40
                        },
                        "textAlign": "right"
                      }
                    },
                    "subtitleText": "This Side Ujjawal",
                    "target": "feedback_input",
                    "titleText": "Hello",
                    "url": null
                  },
                  {
                    "_id": "fe3a0172-3f08-4ab9-a567-0971c171019e",
                    "arrowPosition": "left",
                    "clickAction": "nextStep",
                    "ctaText": "Mohan",
                    "enableBackdrop": true,
                    "eventName": "",
                    "link": "https://google.com",
                    "order": 1,
                    "position": "",
                    "styling": {
                      "appearance": {
                        "arrowStyle": {
                          "height": 12,
                          "width": 6
                        },
                        "backdropOpacity": 70,
                        "colors": {
                          "arrow": "#fe6b35",
                          "backdrop": "#000000",
                          "tooltip": "#0000ff"
                        },
                        "cornerRadius": {
                          "bottomLeft": 12,
                          "bottomRight": 12,
                          "topLeft": 12,
                          "topRight": 12
                        },
                        "highlight": {
                          "padding": 4,
                          "radius": 12
                        },
                        "padding": {
                          "bottom": 0,
                          "left": 0,
                          "right": 0,
                          "top": 0
                        },
                        "width": 100
                      },
                      "cta": {
                        "container": {
                          "alignment": "center",
                          "backgroundColor": "#f7921c",
                          "borderColor": "#fe6b35",
                          "borderWidth": 2,
                          "ctaFullWidth": false,
                          "ctaWidth": 120,
                          "height": 50
                        },
                        "cornerRadius": {
                          "bottomLeft": 12,
                          "bottomRight": 12,
                          "topLeft": 12,
                          "topRight": 12
                        },
                        "margin": {
                          "bottom": 4,
                          "left": 4,
                          "right": 4,
                          "top": 4
                        },
                        "text": {
                          "color": "#ffffff",
                          "fontFamily": "Arial",
                          "fontSize": 14
                        }
                      },
                      "subTitle": {
                        "color": "#ffffff",
                        "fontFamily": "Arial",
                        "fontSize": 12,
                        "margin": {
                          "bottom": 4,
                          "left": 4,
                          "right": 4,
                          "top": 4
                        },
                        "textAlign": "center"
                      },
                      "title": {
                        "color": "#ffffff",
                        "fontFamily": "Arial",
                        "fontSize": 14,
                        "margin": {
                          "bottom": 4,
                          "left": 4,
                          "right": 4,
                          "top": 4
                        },
                        "textAlign": "center"
                      }
                    },
                    "subtitleText": "Raghav",
                    "target": "dropdown",
                    "titleText": "Hi ",
                    "url": null
                  }
                ]
              },
              "display_trigger": true,
              "id": "9d3e2554-c3cf-4370-b154-54500d66d063",
              "screen": "Home Screen Flutter",
              "trigger_event": ""
            }
        """
    }
}