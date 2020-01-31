<template>
  <div>
    <el-button
      :size="btnSize"
      :disabled="btnDisabled"
      :type="btnType"
      :loading="loading"
      @click="dialogVisible = true"
    >{{ btnText }}</el-button>
    <el-dialog
      :title="title"
      :visible.sync="dialogVisible"
      width="30%"
    >
      <slot />
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleSuccess">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  props: {
    btnDisabled: {
      type: Boolean,
      default: false
    },
    success: {
      type: Function,
      default: () => { console.error('回调函数未配置') }
    },
    title: {
      type: String,
      default: '标题'
    },
    body: {
      type: String,
      default: '主题'
    },
    btnText: {
      type: String,
      default: '按钮'
    },
    btnSize: {
      type: String,
      default: 'small'
    },
    btnType: {
      type: String,
      default: 'primary'
    }
  },
  data() {
    return {
      loading: false,
      dialogVisible: false
    }
  },
  computed: {

  },
  watch: {},
  methods: {
    handleSuccess() {
      this.success()
      this.dialogVisible = !this.dialogVisible
    }
  }
}
</script>

<style lang="scss" type="text/scss" scoped>

</style>
