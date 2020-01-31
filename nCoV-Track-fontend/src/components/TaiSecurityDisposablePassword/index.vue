<template>
  <div>
    <el-form
      ref="loginForm"
      :model="loginForm"
      auto-complete="on"
      label-position="left"
    >
      <el-form-item prop="disposablePassword">
        <span class="svg-container">
          <svg-icon icon-class="钥匙" />
        </span>
        <el-input
          ref="disposablePassword"
          v-model="loginForm.disposablePassword"
          placeholder="一次性密码"
          name="disposablePassword"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>
      <el-button
        :loading="loading"
        type="primary"
        style="width:100%;margin-bottom:30px;"
        @click.native.prevent="handleLoginDisposablePassword"
      >
        登录
      </el-button>
    </el-form>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'TaiSecurityDisposablePassword',
  props: {
    success: {
      type: Function,
      default: () => {
      }
    }
  },
  data() {
    return {
      loading: false,
      loginForm: {
        disposablePassword: ''
      }
    }
  },
  computed: {
    ...mapGetters([
      'platformUsername'
    ])
  },
  mounted() {
  },
  methods: {
    handleLoginDisposablePassword() {
      this.loading = true
      const param = {
        otp: this.loginForm.disposablePassword,
        platformUsername: this.platformUsername
      }
      this.$store.dispatch('user/loginDisposablePassword', param)
        .then((res) => {
          this.loading = false
          if (res === 200) {
            this.$router.push({ path: this.redirect || '/' })
          } else if (res === 404) {
            this.$message({
              message: '一次性密码验证错误',
              type: 'warning'
            })
          } else if (res === 300) {
            this.$message({
              message: '用户未激活',
              type: 'warning'
            })
          }
        })
        .catch(() => {
          this.loading = false
        })
    }
  }
}
</script>

<style lang="scss" type="text/scss" scoped>
  $dark_gray: #889aa4;
  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }
</style>
