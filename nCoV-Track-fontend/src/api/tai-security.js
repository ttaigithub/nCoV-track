import request from './ajax/tai-security'

export function loginDisposablePassword(params) {
  return request.post('/taiSecurity/login', params)
}

export function getQrCode(params) {
  return request.post('/taiSecurity/getQrCode', params)
}

export function loginQrCode(params) {
  return request.post('/taiSecurity/qrcodeLogin', params)
}
