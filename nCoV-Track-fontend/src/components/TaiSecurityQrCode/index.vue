<template>
  <div>
    <el-card v-loading="loading" class="wrap">
      <img class="qrCode" :src="qrCode" alt="">
      <el-button class="refresh" type="success" icon="el-icon-refresh" circle @click="getQrCode" />
    </el-card>
  </div>
</template>

<script>
import { getQrCode } from '@/api/tai-security'
import { mapGetters } from 'vuex'

export default {
  name: 'TaiSecurityQrCode',
  props: {
    success: {
      type: Function,
      default: () => {
      }
    }
  },
  data() {
    return {
      loading: true,
      qrCode: '',
      qrId: ''
    }
  },
  computed: {
    ...mapGetters([
      'platformUsername'
    ])
  },
  mounted() {
    this.getQrCode()
  },
  methods: {
    getQrCode() {
      this.loading = true
      getQrCode().then(res => {
        this.qrCode = res.data.qrCode
        this.qrId = res.data.qrId
        this.loading = false
        this.login()
      })
    },
    login() {
      const param = {
        qrId: this.qrId,
        platformUsername: this.platformUsername
      }
      this.$store.dispatch('user/loginQrCode', param).then((res) => {
        if (res === 200) {
          this.$router.push({ path: this.redirect || '/' })
        } else if (res === 300) {
          this.$message.warning('用户未激活')
          this.getQrCode()
        }
      })
    }
  }
}
</script>

<style lang="scss" type="text/scss" scoped>
  /*.card {*/
  .wrap {
    position: relative;
    cursor: pointer;
    width: 340px;
    height: 340px;
    padding: 0;
    margin: 0 auto;
    &:hover {
      opacity: 0.6;
      filter: alpha(opacity=60);
      /*background: rgba(0,0,0,0.3);*/
      .refresh {
        display: block;
        position: absolute;
        top: 150px;
        left: 150px;
      }
    }
    .refresh {
      z-index: 100;
      display: none;
    }
  }

  .qrCode {
    height: 300px;
  }

  /*}*/

</style>
