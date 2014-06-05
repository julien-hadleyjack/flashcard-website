//TODO: Im Moment noch Toggle-Funktionalität, später: Auswertung des value und Entscheidung ob Signup oder Login
function typeWatch()
{
  if(document.getElementById('loginId').value === 'Login') {
    document.getElementById('loginId').value = 'Signup';
      
  }
    else {
        document.getElementById('loginId').value = 'Login';
    }
}