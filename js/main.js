$(document).ready(function () {
  $('#auth-dropdown-toggle').on('click', () => {
    $('.auth-dropdown').toggle();
  });

  $('body').on('click', (e) => {
    const target = $(e.target);
    if (target.attr('id') != 'auth-dropdown-toggle' &&
        target.attr('class') != 'auth-dropdown') {
      $('.auth-dropdown').hide();
    }
  })
});
