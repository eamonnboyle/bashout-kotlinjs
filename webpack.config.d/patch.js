// INTERESTING - We can customise webpack with various JS files in this folder

// Removes some warnings during compilation
config.resolve.alias = {
    "crypto": false,
    "text-encoding": false,
}

// Setup redirects for Single Page Application routing - fallback to index.html
if (config.devServer) {
    config.devServer.historyApiFallback = {
        index: 'index.html'
    };
}

// Allows styles and other modules in resources to be picked up
config.resolve.modules.push("src/main/resources");

// INTERESTING - Some features present in create-react-app are not configured by default
//              For example, file loading in webpack.
config.module.rules.push({
    test: /\.(png|jpe?g|gif)$/i,
    use: [
        {
            loader: 'file-loader',
        },
    ],
});
