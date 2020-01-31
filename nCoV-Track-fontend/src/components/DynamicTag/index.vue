<template>
  <div>
    <el-tag
      v-for="(tag,index) in dynamicTags"
      :key="index"
      :closable="!disabled"
      :disable-transitions="false"
      @close="handleClose(tag)"
    >
      {{ tag }}
    </el-tag>
    <div v-if="!disabled" style="display: inline-block">
      <el-input
        v-if="inputVisible"
        ref="saveTagInput"
        v-model="inputValue"
        class="input-new-tag"
        size="small"
        @keyup.enter.native="handleInputConfirm"
        @blur="handleInputConfirm"
      />
      <el-button
        v-else
        class="button-new-tag"
        type="primary"
        size="small"
        icon="el-icon-plus"
        @click="showInput"
      >
        新的标签
      </el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TaiSecurityQrCode',
  props: {
    tags: {
      type: Array,
      default: () => {
        return []
      }
    },
    disabled: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      dynamicTags: [],
      inputVisible: false,
      inputValue: ''
    }
  },
  computed: {},
  watch: {
    dynamicTags() {
      this.$emit('update:tags', this.dynamicTags)
    }
  },
  mounted() {
    this.dynamicTags = this.tags
  },
  methods: {
    handleClose(tag) {
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1)
    },

    showInput() {
      this.inputVisible = true
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus()
      })
    },

    handleInputConfirm() {
      const inputValue = this.inputValue
      if (inputValue) {
        this.dynamicTags.push(inputValue)
      }
      this.inputVisible = false
      this.inputValue = ''
    }
  }
}
</script>

<style lang="scss" type="text/scss" scoped>
  .el-tag + .el-tag {
    margin-left: 10px;
  }

  .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }

  .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }
</style>
