<template>
  <div style="display: inline-block">
    <el-button
      size="small"
      type="primary"
      round
      icon="el-icon-circle-plus-outline"
      @click="handleDrawer"
    >新增数据集
    </el-button>
    <el-drawer
      title="新增数据集"
      :visible.sync="drawer"
      size="80%"
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
              <el-row :gutter="16">
                <el-col :span="12">
                  <el-form-item label="数据集名称" prop="name">
                    <el-input v-model="formBasicInfo.name" placeholde="请输入数据集名称" />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="数据集描述" prop="description">
                    <el-input v-model="formBasicInfo.description" placeholde="请输入数据集描述" />
                  </el-form-item>
                </el-col>
                <el-col :span="24">
                  <el-form-item label="标签">
                    <dynamic-tag :tags.sync="formBasicInfo.tag" />
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </el-card>
          <el-card class="field">
            <div slot="header" class="head">
              <span>字段配置</span>
              <UploadFile
                :success="uploadSuccess"
                :upload-file-url="uploadFileUrl"
                accept=".csv"
                size="mini"
                :round="true"
                class="upload"
              />
            </div>
            <el-row v-for="(item ,index) in formField" :key="item.name" :gutter="32">
              <el-form
                :ref="`formField[${index}]`"
                :model="item"
                :rules="rulesField"
                label-width="80px"
                size="small"
              >
                <el-col :span="8">
                  <el-form-item label="字段名称">
                    <el-input v-model="item.name" disabled />
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="字段描述" prop="description">
                    <el-input v-model="item.description" />
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="可用方法" prop="matchMethodId">
                    <el-select v-model="item.matchMethodId" multiple>
                      <el-option
                        v-for="option in optionsMatchMethod"
                        :key="option.value"
                        :label="option.label"
                        :value="option.value"
                      />
                    </el-select>
                  </el-form-item>
                </el-col>
                <!-- <el-col :span="6">
                   <el-form-item label="最终结果" prop="resultTypeId">
                     <el-select v-model="item.resultTypeId">
                       <el-option
                         v-for="option in optionsResultTypeId"
                         :key="option.value"
                         :label="option.label"
                         :value="option.value"
                       />
                     </el-select>
                   </el-form-item>
                 </el-col>-->
              </el-form>
            </el-row>
          </el-card>
        </div>
        <div class="bottom">
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
import DynamicTag from '@/components/DynamicTag/index'
import UploadFile from '@/components/UploadFile/index'
import {
  uploadPrivateDatasetUrl,
  addPrivateDataset
} from '@/api/template'

export default {
  name: 'Dashboard',
  components: { DynamicTag, UploadFile },
  props: {
    success: {
      type: Function,
      default: () => {
      }
    }
  },
  data() {
    return {
      drawer: false,
      loading: false,
      formBasicInfo: {
        name: '',
        description: '',
        openToDemandSide: true,
        openToTtai: false,
        tag: [],
        path: ''
      },
      formField: [],
      optionsOpenToDemandSide: [
        {
          value: true,
          label: '公开'
        },
        {
          value: false,
          label: '隐藏'
        }
      ],
      optionsOpenToTtai: [
        {
          value: true,
          label: '加入'
        },
        {
          value: false,
          label: '放弃'
        }
      ],
      optionsMatchMethod: [
        {
          value: 1,
          label: '相同'
        },
        {
          value: 2,
          label: '大于'
        },
        {
          value: 3,
          label: '小于'
        },
        {
          value: 4,
          label: '相等'
        }
      ],
      optionsResultTypeId: [
        {
          value: 1,
          label: '原字段'
        },
        {
          value: 2,
          label: '支持隐私计算'
        },
        {
          value: 3,
          label: '不支持隐私计算'
        },
        {
          value: 4,
          label: '不显示'
        }
      ],
      rulesBasicInfo: {
        name: [
          { required: true, message: '请输入数据集名称', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '请输入数据集描述', trigger: 'blur' }
        ]
      },
      rulesField: {
        description: [
          { required: true, message: '请输入字段描述', trigger: 'blur' }
        ],
        matchMethodId: [
          { type: 'array', required: true, message: '请输入可用方法', trigger: 'blur' }
        ],
        resultTypeId: [
          { required: true, message: '请输入最终结果', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    uploadFileUrl() {
      return uploadPrivateDatasetUrl()
    }
  },
  watch: {},
  mounted() {
  },
  methods: {
    refresh() {
      this.formBasicInfo = {
        name: '',
        description: '',
        openToDemandSide: true,
        openToTtai: false,
        tag: [],
        path: ''
      }
      this.formField = []
    },
    // upload
    uploadSuccess(res) {
      this.formBasicInfo.path = res.data.csvDto.index
      this.formField = res.data.csvDto.fields.map(item => {
        return {
          name: item,
          description: '',
          matchMethodId: [1],
          resultTypeId: 1
        }
      })
    },
    // 提交
    submit() {
      Promise.all([
        this.verifyFormBasicInfo(),
        this.verifyPath(),
        this.verifyFormField()
      ])
        .then(() => {
          this.$confirm('确定要提交表单吗')
            .then(() => {
              this.addDataset()
            })
        })
        .catch((error) => {
          switch (error) {
            case 1:
              this.$notify.warning('请完整填写基础信息')
              break
            case 2:
              this.$notify.warning('请上传文件')
              break
            case 3:
              this.$notify.warning('请完整配置字段')
              break
          }
        })
    },
    verifyFormBasicInfo() {
      return new Promise((resolve, reject) => {
        this.$refs.formBasicInfo.validate((valid) => {
          if (valid) {
            resolve()
          } else {
            reject(1)
          }
        })
      })
    },
    verifyPath() {
      return new Promise((resolve, reject) => {
        if (this.formBasicInfo.path === '') {
          reject(2)
        }
        resolve()
      })
    },
    verifyFormField() {
      return new Promise((resolve, reject) => {
        for (let i = 0; i < this.formField.length; i++) {
          this.$refs[`formField[${i}]`][0].validate((valid) => {
            if (!valid) {
              reject(3)
            }
          })
        }
        resolve(1)
      })
    },
    addDataset() {
      const datasetInfoDTO = Object.assign({}, this.formBasicInfo)
      let fieldInfoDTOList = JSON.parse(JSON.stringify(this.formField))
      datasetInfoDTO.tag = JSON.stringify(datasetInfoDTO.tag)
      fieldInfoDTOList = fieldInfoDTOList.map(item => {
        item.matchMethodId = JSON.stringify(item.matchMethodId)
        return item
      })
      const param = {
        data: JSON.stringify({
          datasetInfoDTO: datasetInfoDTO,
          fieldInfoDTOList: fieldInfoDTOList
        })
      }
      this.loading = true
      addPrivateDataset(param)
        .then(res => {
          this.loading = false
          if (res.code === 0) {
            this.$notify.success('新增数据集成功')
            this.handleDrawer()
            this.success()
            this.refresh()
          } else {
            this.$notify.warning('服务器繁忙，请重试')
          }
        })
    },
    handleDrawer() {
      this.drawer = !this.drawer
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
      .bottom-right {
        float: right;
        .el-button {
          width: 100px;
        }
      }

    }
  }
</style>
