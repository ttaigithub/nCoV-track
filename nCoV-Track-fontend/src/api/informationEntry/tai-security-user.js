import request from '../ajax/server'

export function queryUser(params) {
  return request.post('/taiSecurity/query', params)
}

export function queryUserAll(params) {
  return request.post('/taiSecurity/list', params)
}

