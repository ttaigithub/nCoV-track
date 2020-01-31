import request from '../ajax/server'

export function queryByTime(params) {
  return request.post('/data/queryByTime', params)
}
export function queryLocationByTime(params) {
  return request.post('/data/queryLocationByTime', params)
}
export function addData(params) {
  return request.post('/data/add', params)
}

export function updateData(params) {
  return request.post('/data/update', params)
}

export function deleteData(params) {
  return request.post('/data/delete', params)
}
