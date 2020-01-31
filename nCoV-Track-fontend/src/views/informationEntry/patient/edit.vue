<template>
  <div style="display: inline-block">
    <el-button
      size="small"
      type="text"
      @click="handleDrawer"
    >更多
    </el-button>
    <el-drawer
      title="修改病患"
      :visible.sync="drawer"
      size="50%"
    >
      <div class="drawer">
        <div class="body">
          <el-card header="基础信息">
            <el-form
              ref="formBasicInfo"
              :model="formBasicInfo"
              :rules="rulesBasicInfo"
              label-width="120px"
            >
              <el-form-item label="姓名" prop="patientName">
                <el-input v-model="formBasicInfo.patientName" placeholde="请输入名称" />
              </el-form-item>
              <el-form-item label="状态" prop="state">
                <el-select
                  v-model="formBasicInfo.state"
                  placeholder="请选择状态"
                >
                  <el-option
                    v-for="item in optionsState"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item label="年龄" prop="age">
                <el-input-number v-model="formBasicInfo.age" placeholde="请输入年龄" style="width: 100%" />
              </el-form-item>
              <el-form-item label="性别" prop="sex">
                <el-select
                  v-model="formBasicInfo.sex"
                  placeholder="请选择性别"
                >
                  <el-option
                    v-for="item in optionsSex"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>
            </el-form>
          </el-card>
          <el-card header="活动记录">
            <el-card
              v-for="(item1,index1) in formActivationRecord"
              :key="index1"
              header="时间"
            >
              <div slot="header">
                <span>#{{ index1+1 }}</span>
                <el-button
                  v-if="index1!==0"
                  type="danger"
                  icon="el-icon-delete"
                  circle
                  style="float: right;margin-top: -3px"
                  size="mini"
                  @click="deleteActivationRecord(index1)"
                />
              </div>
              <el-form
                :ref="`formActivationRecord[${index1}]`"
                :model="formActivationRecord[index1]"
                :rules="rulesAddress"
                label-width="120px"
                label-position="left"
              >
                <el-form-item label="日期" prop="time">
                  <el-date-picker
                    v-model="formActivationRecord[index1].time"
                    type="date"
                    style="width: 100%;"
                    placeholder="选择日期"
                    value-format="timestamp"
                  />
                </el-form-item>
                <el-form-item
                  v-for="(item2,index2) in item1.address"
                  :key="index2"
                  label="地点"
                  prop="address"
                >
                  <el-row>
                    <el-col :span="20">
                      <el-input v-model="formActivationRecord[index1].address[index2].name" placeholde="请输入名称" />
                    </el-col>
                    <el-col :span="4" style="text-align: center">
                      <el-button
                        v-if="index2!==0"
                        type="danger"
                        icon="el-icon-delete"
                        circle
                        @click="deleteAddress(index1,index2)"
                      />
                    </el-col>
                  </el-row>
                </el-form-item>
                <el-button
                  type="primary"
                  @click="addAddress(index1)"
                >
                  添加地点
                </el-button>
              </el-form>
            </el-card>
            <el-button
              type="primary"
              @click="addActivationRecord"
            >
              添加活动记录
            </el-button>
          </el-card>
        </div>
        <div class="bottom">
          <div class="bottom-left">
            <el-button
              :loading="loading"
              type="danger"
              round
              icon="el-icon-delete-solid"
              @click="deleteOne"
            >删除
            </el-button>
          </div>
          <div class="bottom-right">
            <el-button
              :loading="loading"
              type="primary"
              round
              icon="el-icon-circle-check"
              @click="submit"
            >确定
            </el-button>
            <el-button
              round
              @click="handleDrawer"
            >取消
            </el-button>
          </div>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import {
  deletePatient
} from '@/api/informationEntry/patient'
import {
  queryTrackDetail,
  updateTrack
} from '@/api/informationEntry/track'
import _ from 'lodash'

export default {
  name: 'Dashboard',
  components: {},
  props: {
    basicInfo: {
      type: Object,
      default: () => {
        return {}
      }
    },
    success: {
      type: Function,
      default: () => {
      }
    }
  },
  data() {
    const validateAddress = (rule, value, callback) => {
      for (let i = 0; i < value.length; i++) {
        if (value[i] === '') {
          callback(new Error('请输入地址'))
        } else {
          callback()
        }
      }
    }
    return {
      drawer: false,
      loading: false,
      formBasicInfo: {
        patientName: '',
        state: 0,
        age: '',
        sex: 0
      },
      formActivationRecord: [
        {
          time: '',
          address: [{ name: '' }]
        }
      ],
      rulesBasicInfo: {
        patientName: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ],
        state: [
          { required: true, message: '请输入描述', trigger: 'blur' }
        ],
        age: [
          { required: true, message: '请输入URL', trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '请选择父权限', trigger: 'blur' }
        ]
      },
      rulesAddress: {
        time: [
          { required: true, message: '请输入日期', trigger: 'blur' }
        ],
        address: [
          { required: true, validator: validateAddress, trigger: 'blur' }
        ]
      },
      optionsState: [
        {
          value: 0,
          label: '疑似'
        },
        {
          value: 1,
          label: '确诊'
        },
        {
          value: 2,
          label: '死亡'
        },
        {
          value: 3,
          label: '治愈'
        },
        {
          value: 4,
          label: '可能传播者'
        }
      ],
      optionsSex: [
        {
          value: 0,
          label: '女'
        },
        {
          value: 1,
          label: '男'
        }
      ]
    }
  },
  computed: {},
  watch: {
    drawer() {
      if (this.drawer) {
        this.formBasicInfo = JSON.parse(JSON.stringify(this.basicInfo))
        this.queryActivationRecordList()
      }
    }
  },
  mounted() {
  },
  methods: {
    // 提交
    submit() {
      Promise.all([
        this.validateBasicInfo(),
        this.validateActivationRecord()
      ])
        .then(() => {
          this.$confirm('确定要提交表单吗')
            .then(() => {
              this.update()
            })
        })
    },
    update() {
      this.loading = true
      const param = {
        data: JSON.stringify({
          patientData: this.formBasicInfo,
          addressData: this.formActivationRecord
        })
      }
      updateTrack(param)
        .then(res => {
          this.loading = false
          if (res.code === 0) {
            this.$notify.success('修改患者成功')
            this.handleDrawer()
            this.success()
          } else {
            this.$notify.warning('服务器繁忙，请重试')
          }
        })
    },
    queryActivationRecordList() {
      const param = { data: JSON.stringify({ id: this.formBasicInfo.id }) }
      queryTrackDetail(param)
        .then(res => {
          if (res.code === 0) {
            this.formActivationRecord = this.formatActivationRecordList(res.data.addressData)
          } else {
            this.$notify.warning('服务器繁忙，请重试')
          }
        })
    },
    formatActivationRecordList(addressData) {
      const hash = {}
      const formActivationRecord = []
      addressData.forEach(item => {
        if (hash[item.appearTime] === undefined) {
          hash[item.appearTime] = []
          hash[item.appearTime].push(item.departure)
          hash[item.appearTime].push(item.destination)
        } else {
          hash[item.appearTime].push(item.departure)
          hash[item.appearTime].push(item.destination)
        }
      })
      for (const key in hash) {
        formActivationRecord.push({
          time: key,
          address: _.uniq(hash[key]).map(addressName => {
            return {
              name: addressName
            }
          })
        })
      }
      return formActivationRecord
    },
    deleteOne() {
      this.$confirm('删除该病患')
        .then(() => {
          const param = { data: JSON.stringify({ id: this.formBasicInfo.id }) }
          deletePatient(param)
            .then(res => {
              if (res.code === 0) {
                this.$notify.success('删除权限成功')
                this.handleDrawer()
                this.success()
              } else {
                this.$notify.warning('服务器繁忙，请重试')
              }
            })
        })
    },
    // 抽屉开关
    handleDrawer() {
      this.drawer = !this.drawer
    },
    addAddress(index1) {
      this.formActivationRecord[index1].address.push({ name: '' })
    },
    deleteAddress(index1, index2) {
      this.formActivationRecord[index1].address.splice(index2, 1)
    },
    addActivationRecord() {
      this.formActivationRecord.push({
        time: '',
        address: [{ name: '' }]
      })
    },
    deleteActivationRecord(index1) {
      this.formActivationRecord.splice(index1, 1)
    },
    validateBasicInfo() {
      return new Promise((resolve, reject) => {
        this.$refs.formBasicInfo.validate((valid) => {
          if (valid) {
            resolve()
          } else {
            reject()
          }
        })
      })
    },
    validateActivationRecord() {
      return new Promise((resolve, reject) => {
        for (let i = 0; i < this.formActivationRecord.length; i++) {
          this.$refs[`formActivationRecord[${i}]`][0].validate((valid) => {
            if (!valid) {
              reject(3)
            }
          })
        }
        resolve()
      })
    }
  }
}
</script>

<style lang="scss" type="text/scss" scoped>
  .drawer {
    position: relative;
    margin: 0 32px;
    height: 100%;
    .body {
      height: calc(100vh - 150px);
      overflow-y: scroll;
      .el-card {
        margin-bottom: 16px;
      }
      .field {
        .head {
          .upload {
            float: right;
            margin-top: -4px;
          }
        }
      }
    }
    .bottom {
      border-top: 1px #E4E7ED dashed;
      height: 60px;
      line-height: 60px;
      width: 100%;
      position: absolute;
      bottom: 0;
      z-index: 100;
      .bottom-left {
        float: left;
        .el-button {
          width: 100px;
        }
      }
      .bottom-right {
        float: right;
        .el-button {
          width: 100px;
        }
      }

    }
  }
</style>
