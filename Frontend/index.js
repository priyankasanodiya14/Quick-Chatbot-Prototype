document.getElementById('generateButton').addEventListener('click', async () => {
    const userInput = document.getElementById('userInput').value;
  
    try {
      const response = await fetch(`http://localhost:8080/chat?prompt=${userInput}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ userInput })
      });
      
      const data = await response.json();
      console.log(data);
      chatDisplay(data.content);
    } catch (error) {
      console.error('Error generating chat:', error.message);
    }
  });
  
  function displayChat(chatbox) {
    const chatDisplay = document.getElementById('chatDisplay');
    chatDisplay.innerText = chatbox;
    chatDisplay.style.display = 'block';
  }
  