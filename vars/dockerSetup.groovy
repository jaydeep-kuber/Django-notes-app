def call() {
    echo "Setting up Docker on the agent..."

    // Check if Docker is installed
    def dockerCheck = sh(script: 'docker --version', returnStatus: true)
    if (dockerCheck == 0) {
        echo "Docker is already installed!"
    } else {
        echo "Docker not found. Installing Docker..."

        // Update package list and install Docker (Ubuntu/Debian example)
        sh '''
            sudo apt-get update -y
            sudo apt-get install -y docker.io
            sudo systemctl start docker
            sudo systemctl enable docker
        '''
        echo "Docker installed successfully!"
    }

    // // Add Jenkins user to docker group for permissions
    // echo "Granting Docker permissions to the Jenkins user..."
    // sh '''
    //     sudo usermod -aG docker $USER
    // '''
    // echo "Docker setup complete! Jenkins can now run Docker commands."
}