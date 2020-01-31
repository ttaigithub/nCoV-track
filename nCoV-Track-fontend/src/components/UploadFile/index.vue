<template>
  <div>
    <el-upload
      :action="uploadFileUrl"
      :before-upload="handleBeforeUpload"
      :on-success="handleOnSuccess"
      :on-error="handleError"
      :multiple="multiple"
      :drag="drag"
      :show-file-list="false"
      :accept="accept"
    >
      <el-button
        v-if="!drag"
        :size="size"
        icon="el-icon-upload"
        :type="type"
        :loading="loading"
        :round="round"
      >{{ buttonInfo }}
      </el-button>
      <div v-if="drag">
        <i class="el-icon-upload" />
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      </div>
    </el-upload>
  </div>
</template>

<script>
export default {
  props: {
    success: {
      type: Function,
      default: () => {
        return null
      }
    },
    error: {
      type: Function,
      default: () => {
        return null
      }
    },
    uploadFileUrl: {
      type: String,
      default: ''
    },
    multiple: {
      type: Boolean,
      default: true
    },
    drag: {
      type: Boolean,
      default: false
    },
    size: {
      type: String,
      default: 'small'
    },
    type: {
      type: String,
      default: 'small'
    },
    accept: {
      type: String,
      default: ''
    },
    round: {
      type: Boolean,
      default: true
    },
    buttonInfo: {
      type: String,
      default: '文件上传'
    }
  },
  data() {
    return {
      loading: false
    }
  },
  computed: {},
  watch: {},
  methods: {
    handleBeforeUpload() {
      this.loading = true
    },
    handleOnSuccess(response) {
      this.loading = false
      this.$notify({
        type: 'success',
        message: '文件上传成功'
      })
      this.success(response)
    },
    handleError(err) {
      this.loading = false
      this.$notify({
        type: 'error',
        message: '文件上传失败'
      })
      this.error(err)
    }
  }
}
</script>

<style lang="scss" type="text/scss" scoped>

</style>
