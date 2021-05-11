module.exports = {
  devServer: {
    port: 8081,
    https: false,
    hotOnly: false,
  },
  pages: {
    index: {
      entry: 'src/index/main.js',
      template: 'public/index.html',
      filename: 'index.html',
      title: 'Get Your Tour',
      chunks: ['chunk-vendors', 'chunk-common', 'index']
    },
    tour: {
      entry: 'src/tour/main.js',
      template: 'public/tour.html',
      filename: 'tour.html',
      title: 'Get Your Tour/Tour',
      chunks: ['chunk-vendors', 'chunk-common', 'tour']
    },
    shop: {
      entry: 'src/shop/main.js',
      template: 'public/shop.html',
      filename: 'shop.html',
      title: 'Get Your Tour/Shop',
      chunks: ['chunk-vendors', 'chunk-common', 'shop']
    },
    admin: {
      entry: 'src/admin/main.js',
      template: 'public/admin.html',
      filename: 'admin.html',
      title: 'Get Your Tour/Admin',
      chunks: ['chunk-vendors', 'chunk-common', 'admin']
    }
  }
}