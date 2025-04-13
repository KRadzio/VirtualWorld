#pragma once
#include "Animal.h"

#define STRENGTH 5
#define INITIATIVE 4
#define SYMBOL 'C'
#define MOVE_RANGE 1
/*
Magiczny Eliksir:
Siła Człowieka rośnie do 10 w pierwszej turze
działania umiejętności. W każdej kolejnej turze maleje
o „1”, aż wróci do stanu początkowego. */
#define SPECIAL_ABILITY_DURATION 5
#define SPECIAL_ABILITY_COOLDOWN 5

class Human : public Animal
{
public:
  Human(World &wR, const int &xPos, const int &yPos);
  void activateAbility();
  void handleCooldown();
  void handleAbility();
  virtual void takeAction() override;
  ~Human();

private:
  int extraStrength = 0;
  int abilityCoolDown = 0;
};