<template>
  <div class="login-container">
    <div class="body">
      <div class="title-container">
        <h3 class="title" @click="fakeLogin">Tai健康</h3>
        <h3 class="title">冠状病毒实时监控</h3>
      </div>
    </div>
  </div>
</template>

<script>
import TaiSecurityDisposablePassword from '@/components/TaiSecurityDisposablePassword/index.vue'
import TaiSecurityQrCode from '@/components/TaiSecurityQrCode/index.vue'
import { mapGetters } from 'vuex'

export default {
  name: 'Login',
  components: {},
  data() {
    return {
      loginForm: {
        disposablePassword: ''
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined,
      loginMethod: 'disposablePassword'
      // loginMethod: 'qrCode'
    }
  },
  computed: {
    ...mapGetters([
      'platformUsername'
    ])
  },
  watch: {},
  mounted() {
    // this.getPlatformUserName()
    this.fakeLogin()
  },
  methods: {
    login() {
      this.$store.dispatch('user/login', this.loginForm).then(() => {
        this.$router.push({ path: this.redirect || '/' })
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
    },
    fakeLogin() {
      this.$store.dispatch('user/fakeLogin', this.loginForm).then(() => {
        this.$router.push({ path: this.redirect || '/' })
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
    },
    changeLoginMethod(e) {
      this.loginMethod = e
    },
    getPlatformUserName() {
      this.$store.dispatch('platform/queryPlatformUsername')
    }
  }
}
</script>

<style lang="scss" type="text/scss">
  /* 修复input 背景不协调 和光标变色 */
  /* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

  $bg: #283443;
  $light_gray: #fff;
  $cursor: #fff;

  @supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
    .login-container .el-input input {
      color: $cursor;
    }
  }

  /* reset element-ui css */
  .login-container {
    .el-input {
      display: inline-block;
      height: 47px;
      width: 85%;

      input {
        background: transparent;
        border: 0px;
        -webkit-appearance: none;
        border-radius: 0px;
        padding: 12px 5px 12px 15px;
        color: $light_gray;
        height: 47px;
        caret-color: $cursor;

        &:-webkit-autofill {
          box-shadow: 0 0 0px 1000px $bg inset !important;
          -webkit-text-fill-color: $cursor !important;
        }
      }
    }

    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
    }
  }
</style>

<style lang="scss" type="text/scss" scoped>
  $bg: #2d3a4b;
  $dark_gray: #889aa4;
  $light_gray: #eee;

  .login-container {
    min-height: 100%;
    width: 100%;
    background-color: $bg;
    overflow: hidden;
    .title-container {
      position: relative;
      .title {
        font-size: 26px;
        color: $light_gray;
        margin: 0px auto 40px auto;
        text-align: center;
        font-weight: bold;
      }
    }
    .body {
      position: relative;
      width: 520px;
      max-width: 100%;
      padding: 160px 35px 0;
      margin: 0 auto;
      overflow: hidden;
    }

    .tips {
      font-size: 14px;
      color: #fff;
      margin-bottom: 10px;

      span {
        &:first-of-type {
          margin-right: 16px;
        }
      }
    }

    .svg-container {
      padding: 6px 5px 6px 15px;
      color: $dark_gray;
      vertical-align: middle;
      width: 30px;
      display: inline-block;
    }

    .show-pwd {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: $dark_gray;
      cursor: pointer;
      user-select: none;
    }
  }

  .qr-code {
    margin-bottom: 32px;
  }

</style>
