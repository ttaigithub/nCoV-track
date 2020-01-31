<template>
  <div class="app-container">
    <div class="wrap">
      <el-card header="公司注册" class="out-card">
        <el-form
          ref="formBasicInfo"
          :model="formBasicInfo"
          label-width="120px"
        >
          <el-form-item label="公司名称" prop="name">
            <el-input v-model="formBasicInfo.companyName" />
          </el-form-item>
        </el-form>
        <el-button class="bottom-button" type="primary" @click="submit">注册</el-button>
      </el-card>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import {
  registerPlatformUser
} from '@/api/platformUser'

export default {
  name: 'Dashboard',
  data() {
    return {
      formBasicInfo: {
        companyName: ''
      }
    }
  },
  computed: {
    ...mapGetters([
      'platformUsername'
    ])
  },
  watch: {},
  mounted() {
    this.verify()
  },
  methods: {
    submit() {
      this.registerPlatformUser()
    },
    registerPlatformUser() {
      const param = {
        data: JSON.stringify({
          userTypeId: 2,
          companyName: this.formBasicInfo.companyName
        })
      }
      registerPlatformUser(param)
        .then(res => {
          if (res.code === 0) {
            this.verify()
          } else if (res.code === 200004) {
            this.$notify.warning('公司已被注册')
          } else if (res.code === 200005) {
            this.verify()
          }
        })
    },
    verify() {
      this.$store.dispatch('platform/queryPlatformUsername')
      if (this.platformUsername !== undefined && this.platformUsername !== 'null') {
        this.$router.push({ path: this.redirect || '/public-data/dataset' })
      }
    }
  }
}
</script>

<style lang="scss" type="text/scss" scoped>
  .wrap {
    width: 100%;
    height: calc(100vh - 120px);
    .out-card {
      width: 700px;
      position: absolute;
      top: 50%;
      left: 50%;
      margin-left: -350px;
      transform: translateY(-50%);
      .bottom-button {
        display: block;
        margin: 0 auto;
        width: 300px;
      }
    }
  }

</style>
