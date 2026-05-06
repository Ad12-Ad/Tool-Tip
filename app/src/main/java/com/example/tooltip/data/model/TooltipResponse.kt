package com.example.tooltip.data.model

import com.google.gson.annotations.SerializedName

data class TooltipResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("campaign_type") val campaignType: String?,
    @SerializedName("screen") val screen: String?,
    @SerializedName("display_trigger") val displayTrigger: Boolean?,
    @SerializedName("trigger_event") val triggerEvent: String?,
    @SerializedName("details") val details: TooltipDetails?
)

data class TooltipDetails(
    @SerializedName("tooltips") val tooltips: List<TooltipItem>?
)

data class TooltipItem(
    @SerializedName("_id") val id: String?,
    @SerializedName("order") val order: Int?,
    @SerializedName("target") val target: String?, // e.g., "feedback_input"
    @SerializedName("titleText") val titleText: String?,
    @SerializedName("subtitleText") val subtitleText: String?,
    @SerializedName("ctaText") val ctaText: String?,
    @SerializedName("clickAction") val clickAction: String?, // e.g., "nextStep"
    @SerializedName("enableBackdrop") val enableBackdrop: Boolean?,
    @SerializedName("styling") val styling: TooltipStyling?
)

data class TooltipStyling(
    @SerializedName("appearance") val appearance: Appearance?,
    @SerializedName("cta") val cta: CtaStyling?,
    @SerializedName("title") val titleStyling: TextStyling?,
    @SerializedName("subTitle") val subtitleStyling: TextStyling?
)

data class Appearance(
    @SerializedName("backdropOpacity") val backdropOpacity: Int?,
    @SerializedName("colors") val colors: TooltipColors?,
    @SerializedName("cornerRadius") val cornerRadius: CornerRadius?,
    @SerializedName("padding") val padding: Dimensions?
)

data class TooltipColors(
    @SerializedName("arrow") val arrow: String?,
    @SerializedName("backdrop") val backdrop: String?,
    @SerializedName("tooltip") val tooltip: String?
)

data class CtaStyling(
    @SerializedName("container") val container: ContainerStyling?,
    @SerializedName("text") val text: TextStyling?,
    @SerializedName("cornerRadius") val cornerRadius: CornerRadius?,
    @SerializedName("margin") val margin: Dimensions?
)

data class ContainerStyling(
    @SerializedName("alignment") val alignment: String?,
    @SerializedName("backgroundColor") val backgroundColor: String?,
    @SerializedName("borderColor") val borderColor: String?,
    @SerializedName("borderWidth") val borderWidth: Int?
)

data class TextStyling(
    @SerializedName("color") val color: String?,
    @SerializedName("fontFamily") val fontFamily: String?,
    @SerializedName("fontSize") val fontSize: Int?,
    @SerializedName("textAlign") val textAlign: String?,
    @SerializedName("margin") val margin: Dimensions?
)

data class CornerRadius(
    @SerializedName("topLeft") val topLeft: Int?,
    @SerializedName("topRight") val topRight: Int?,
    @SerializedName("bottomLeft") val bottomLeft: Int?,
    @SerializedName("bottomRight") val bottomRight: Int?
)

data class Dimensions(
    @SerializedName("top") val top: Int?,
    @SerializedName("bottom") val bottom: Int?,
    @SerializedName("left") val left: Int?,
    @SerializedName("right") val right: Int?
)