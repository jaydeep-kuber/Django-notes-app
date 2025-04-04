def call() {
    echo "Installing Docker inside the agent container..."

    // Check if Docker is already installed
    def dockerCheck = sh(script: 'docker --version', returnStatus: true)
    if (dockerCheck == 0) {
        echo "Docker is already installed in the container!"
        return
    }

    // Install Docker inside the container (assuming Ubuntu-based image)
    echo "Docker not found. Installing Docker..."
    sh '''
        apt-get update -y
        apt-get install -y docker.io
        systemctl start docker || echo "Systemctl not available, skipping service start (expected in container)"
        echo "Docker installed successfully!"
    '''

    // Verify installation
    sh 'docker --version'
    echo "Docker setup complete inside the agent container!"
}