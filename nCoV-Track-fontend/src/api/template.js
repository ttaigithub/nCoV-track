import request from './ajax/local'

export function queryPrivateDatasetList(params) {
  return request.post('/privateDataset/query', params)
}

export function addPrivateDataset(params) {
  return request.post('/privateDataset/add', params)
}

export function updatePrivateDataset(params) {
  return request.post('/privateDataset/update', params)
}

export function deletePrivateDataset(params) {
  return request.post('/privateDataset/delete', params)
}

export function queryPrivateDatasetField(params) {
  return request.post('/privateDataset/getField', params)
}

export function uploadPrivateDatasetUrl() {
  return process.env.VUE_APP_LOCAL_API + '/privateDataset/upload'
}
