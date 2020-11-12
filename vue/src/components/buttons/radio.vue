<template>
  <div cal>
    <v-radio-group
      :class="`buttonRadio  `"
      v-model="this.bData"
      row
      :hide-details="true"
      @change="change"
    >
      <v-btn
        :class="`ondajiButton secondary ${this.bData ? 'active' : ''}`"
        :rounded="rounded"
        :outlined="outlined"
        @click="change(true)"
        v-ripple="false"
      >
        <div v-if="isRadioImage" class="dotcircle">
          <div class="dot" v-if="value"></div>
        </div>
        <span class="ondaji_font_16_medium_grey_80">
          {{ trueText }}
        </span>
      </v-btn>
      <v-btn
        :class="`ml-4 ondajiButton ${btnType} ${this.bData ? '' : 'active'}`"
        :rounded="rounded"
        :outlined="outlined"
        @click="change(false)"
      >
        <div v-if="isRadioImage" class="dotcircle">
          <div class="dot" v-if="!value"></div>
        </div>
        <span class="ondaji_font_16_medium_grey_80">
          {{ falseText }}
        </span>
      </v-btn>
    </v-radio-group>
  </div>
</template>
<style lang="scss">
.buttonRadio .v-icon {
  font-size: 18px !important;
}
</style>

<style lang="scss" scoped>
$defaultColor: #3e2723;
.v-application .ondajiButton.primary {
  background-color: $defaultColor !important;
}

.v-input--selection-controls {
  margin: 0px;
  padding: 0px;
}
$defaultColor: #3e2723;
.v-application .ondajiButton.secondary {
  background-color: #fff !important;
  color: $defaultColor !important;

  &.active {
    background-color: #e2d9d2 !important;
  }
}

.dotcircle {
  border-radius: 50%;
  border: 1px solid $defaultColor;
  width: 16px;
  height: 16px;
  padding: 2.5px;
  margin-right: 8px;

  .dot {
    border-radius: 50%;
    width: 100%;
    height: 100%;
    background-color: $defaultColor;
  }
}

.v-btn--outlined {
  border: 2px solid $defaultColor;
  border-width: 3px !important;
}

.v-input--radio-group.v-input--radio-group--row .v-radio {
  margin-right: 0px;
}
</style>
<script>
export default {
  props: {
    value: {
      type: Boolean,
      default: true,
    },
    btnType: {
      btnType: String,
      default: "secondary",
    },
    isRadioImage: {
      type: Boolean,
      default: true,
    },
    rounded: {
      type: Boolean,
      default: true,
    },
    outlined: {
      type: Boolean,
      default: true,
    },
    trueText: {
      type: String,
      default: "실내",
    },
    falseText: {
      type: String,
      default: "실외",
    },
  },
  created() {
    this.bData = this.value

    if (this.btnType !== "") {
      switch (this.btnType) {
        case "primary":
          break
        case "secondary":
          this.outlined = true
          break
      }
    }
  },

  data() {
    return {
      bData: false,
    }
  },
  watch: {
    bData(val) {
      console.log(val)
    },
  },
  methods: {
    change(val) {
      this.bData = val
      this.$emit("input", val)
    },
  },
}
</script>
